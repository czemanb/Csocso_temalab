package hu.bme.aut.freelancerandroid.util

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.model.ClusterMarker

class MyClusterManagerRenderer(
    context: Context?,
    map: GoogleMap?,
    clusterManager: ClusterManager<ClusterMarker>?
) : DefaultClusterRenderer<ClusterMarker>(context, map, clusterManager) {

    private val iconGenerator: IconGenerator = IconGenerator(context?.applicationContext)
    private val imageView: ImageView = ImageView(context?.applicationContext)
    private val markerWidth: Int =
        context?.resources?.getDimension(R.dimen.custom_marker_image)?.toInt() ?: 0
    private val markerHeight: Int =
        context?.resources?.getDimension(R.dimen.custom_marker_image)?.toInt() ?: 0

    init {
        imageView.layoutParams = ViewGroup.LayoutParams(markerWidth, markerHeight)
        val padding = context?.resources?.getDimension(R.dimen.custom_marker_padding)?.toInt() ?: 0
        imageView.setPadding(padding, padding, padding, padding)
        iconGenerator.setContentView(imageView)
    }

    override fun onBeforeClusterItemRendered(item: ClusterMarker, markerOptions: MarkerOptions) {
        imageView.setImageResource(item.iconImage)
        val icon = iconGenerator.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(item.title)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).snippet(item.snippet)
    }

    override fun shouldRenderAsCluster(cluster: Cluster<ClusterMarker>): Boolean {
        return false
    }
}