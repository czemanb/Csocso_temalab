package hu.bme.aut.freelancerandroid.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.internal.PolylineEncoding
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.model.ClusterMarker
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel
import hu.bme.aut.freelancerandroid.util.MyClusterManagerRenderer
import kotlinx.android.synthetic.main.fragment_google_maps.*

class GoogleMapsFragment : Fragment(R.layout.fragment_google_maps) , OnMapReadyCallback {

    private lateinit var mapView: MapView
    private var gpsEnabled = false
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var location: Location
    private lateinit var googleMap: GoogleMap
    private var transfer: Transfer? = null
    private lateinit var pickUpPoints: List<LatLng>
    private lateinit var destinationPoints: List<LatLng>
    private lateinit var pickUpTimes: List<String>
    private lateinit var destinationTimes: List<String>
    private lateinit var names: List<String>
    val args: GoogleMapsFragmentArgs by navArgs()
    private lateinit var transferViewModel: TransferViewModel
    private lateinit var mClusterManager: ClusterManager<ClusterMarker>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transferViewModel = (activity as ApplicationActivity).transferViewModel
        transferViewModel.navigationUrl.observe(viewLifecycleOwner) { response ->
            response.data?.let { navigationUrl ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(navigationUrl.navigationUrl))
                startActivity(intent)
            }
        }

        btnStartNavigation.setOnClickListener {
            transferViewModel.fetchTransferNavigationUrl(transfer!!.id, location.latitude, location.longitude)
        }

        transfer = args.transfer
        pickUpPoints = args.pickUpPoints.toList()
        destinationPoints = args.destinations.toList()
        pickUpTimes = args.pickupTimes.toList()
        destinationTimes = args.deliveryTimes.toList()
        names = args.names.toList()

        setTextViewAndButton()

        if ((activity as ApplicationActivity).gpsEnabled)
            gpsEnabled = true

        val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
        mapView = view.findViewById(R.id.google_map)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
    }

    private fun setTextViewAndButton() {
        if (transfer != null) {
            if (transfer!!.encodedRoute != null) {
                if (names.isEmpty()) {
                    btnStartNavigation.visibility = View.GONE
                    noNavData.text = getString(R.string.no_packages)
                } else {
                    noNavData.visibility = View.GONE
                }
            } else {
                btnStartNavigation.visibility = View.GONE
                noNavData.text = getString(R.string.no_nav_data)
            }
        } else {
            btnStartNavigation.visibility = View.GONE
            if (pickUpTimes[0] != "") {
                noNavData.visibility = View.GONE
                val layout = googleMapLayout
                val constraintSet = ConstraintSet()
                constraintSet.clone(layout)
                constraintSet.connect(R.id.google_map, ConstraintSet.TOP, R.id.googleMapLayout, ConstraintSet.TOP)
            } else {
                noNavData.text = getString(R.string.no_package_data)
            }
        }
    }

    private fun getLastKnownLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    this.location = location
                    centerCamera()
                }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY) ?: Bundle().apply {
            putBundle(MAPVIEW_BUNDLE_KEY, this)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ) {
            return
        }
        if (gpsEnabled) {
            getLastKnownLocation()
            map.isMyLocationEnabled = true
        }
        addMapMarkers()
        drawRoute()
    }

    private fun drawRoute() {
        var newDecodedRoute = ArrayList<LatLng>()
        if (transfer != null) {
            if (transfer!!.encodedRoute != null) {
                val decodedRoute = PolylineEncoding.decode(transfer!!.encodedRoute)

                for (latLng in decodedRoute) {
                    newDecodedRoute.add(LatLng(latLng.lat, latLng.lng))
                }
            }

        } else {
            newDecodedRoute.add(pickUpPoints[0])
            newDecodedRoute.add(destinationPoints[0])
        }
        val polyLine = googleMap.addPolyline(PolylineOptions().addAll(newDecodedRoute))
    }

    private fun addMapMarkers(){
        mClusterManager = ClusterManager(requireContext(), googleMap)
        val mClusterManagerRenderer
                = MyClusterManagerRenderer(requireActivity(), googleMap, mClusterManager)
        mClusterManager.renderer = mClusterManagerRenderer

        createMarkers(pickUpPoints, pickUpTimes, R.drawable.box, "Pick up")
        createMarkers(destinationPoints, destinationTimes, R.drawable.clearance, "Delivery")
        if (transfer != null) {
            val clusterMarker = ClusterMarker(
                LatLng(transfer!!.fromLat, transfer!!.fromLong),
                "Starting point", "", R.drawable.house
            )
            mClusterManager.addItem(clusterMarker)
        }
        mClusterManager.cluster()
    }

    private fun createMarkers(points: List<LatLng>, times: List<String>, avatar: Int, routePart: String) {
        var i = 0
        points.forEach { point ->
            val snippet = "$routePart time is ${if (times[i] != "") times[i] else "not calculated yet"}"
            val clusterMarker = ClusterMarker(point, names[i], snippet, avatar)
            mClusterManager.addItem(clusterMarker)
            i++
        }
    }

    private fun centerCamera() {
        val bottomBoundary: Double
        val leftBoundary: Double
        val topBoundary: Double
        val rightBoundary: Double
        if (transfer != null) {
            bottomBoundary = transfer?.fromLat?.minus(.1)!!
            leftBoundary = transfer?.fromLong?.minus(.1)!!
            topBoundary = transfer?.fromLat?.plus(.1)!!
            rightBoundary = transfer?.fromLong?.plus(.1)!!
        } else {
            bottomBoundary = pickUpPoints[0].latitude - .1
            leftBoundary = pickUpPoints[0].longitude - .1
            topBoundary = pickUpPoints[0].latitude + .1
            rightBoundary = pickUpPoints[0].longitude + .1
        }

        val bounds = LatLngBounds(LatLng(bottomBoundary, leftBoundary), LatLng(topBoundary, rightBoundary))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0))
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    }
}