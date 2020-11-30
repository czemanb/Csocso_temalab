package hu.bme.aut.freelancerandroid.repository.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class ClusterMarker(
    private val position: LatLng,
    private val title: String,
    private val snippet: String,
    val iconImage: Int
) : ClusterItem {

    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String? {
        return title
    }

    override fun getSnippet(): String? {
        return snippet
    }
}