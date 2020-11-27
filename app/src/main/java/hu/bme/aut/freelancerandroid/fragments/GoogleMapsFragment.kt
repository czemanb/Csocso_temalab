package hu.bme.aut.freelancerandroid.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel

class GoogleMapsFragment : Fragment(R.layout.fragment_google_maps), OnMapReadyCallback {

//    lateinit var transferViewModel: TransferViewModel
    private lateinit var mapView: MapView
    private var gpsEnabled = false
    private var locationPermissionEnabled = false
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var location: Location
    private lateinit var googleMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkGpsEnabled()
        checkLocationPermissionEnabled()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    this.location = location
                    centerCamera()
                }
            }

//        transferViewModel = requireContext().transferViewModel
//
//        transferViewModel.transfers.observe(viewLifecycleOwner) { response ->
//            response.data?.let { transfers ->
//                transfers.forEach { Log.e("transfer", it.toString()) }
//            }
//        }

        val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
        mapView = view.findViewById(R.id.google_map)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
    }

    private fun checkLocationPermissionEnabled() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(requireContext(),
                    "I need it to access location", Toast.LENGTH_SHORT).show()
            }
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_CODE)
        } else {
            locationPermissionEnabled = true
        }
    }

    private fun checkGpsEnabled() {
        if ((activity as ApplicationActivity).locationEnabled()) {
            buildAlertMessageNoGps()
        } else {
            gpsEnabled = true
        }
    }

    private fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("This page requires GPS to work properly, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                val enableGpsIntent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(enableGpsIntent, PERMISSION_REQUEST_ENABLE_GPS)
            }
            .create()
            .show()
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
        map.addMarker(MarkerOptions().position(LatLng(0.0, 0.0)).title("Marker"))
        googleMap = map

        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ) {
            return
        }
        if (gpsEnabled) {
            map.isMyLocationEnabled = true
        }
    }

    private fun centerCamera() {
        val bottomBoundary = location.latitude - .1
        val leftBoundary = location.longitude - .1
        val topBoundary = location.latitude + .1
        val rightBoundary = location.longitude + .1

        val bounds = LatLngBounds(LatLng(bottomBoundary, leftBoundary), LatLng(topBoundary, rightBoundary))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "Location permission granted",
                        Toast.LENGTH_SHORT).show()
                    mapView.getMapAsync(this)
                } else {
                    Toast.makeText(requireContext(), "Location permission NOT granted",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (PERMISSION_REQUEST_ENABLE_GPS) {
            PERMISSION_REQUEST_ENABLE_GPS -> {
                Log.e("onActivityResult", "itt vagyok")
                gpsEnabled = true
                mapView.getMapAsync(this)
            }
        }
    }

    companion object {
        private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
        private const val PERMISSION_REQUEST_CODE = 1
        private const val PERMISSION_REQUEST_ENABLE_GPS = 1
    }
}