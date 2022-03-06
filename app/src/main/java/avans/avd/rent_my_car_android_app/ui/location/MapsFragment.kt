package avans.avd.rent_my_car_android_app.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import avans.avd.rent_my_car_android_app.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private var mMapView: MapView? = null
    private lateinit var mMap: GoogleMap
    private var _locationBinding: FragmentMapsBinding? = null
    private val locationBinding get() = _locationBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _locationBinding = FragmentMapsBinding.inflate(inflater, container, false)

        return locationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView = locationBinding.mapViewLocation
        mMapView!!.onCreate(mapViewBundle)
        mMapView!!.getMapAsync {
            googleMap -> mMap = googleMap
            onMapReady(mMap)
        }
        locationBinding.spLocations.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position) {
                    0 -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(52.36892552672471, 5.411609050935421), 7f))
                    1 -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(52.08955234572531, 5.109965441115019), 15f))
                    2 -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(52.37919726315386, 4.900437200645117), 15f))
                    3 -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.5846112088578, 4.797199300628366), 15f))
                    4 -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(53.21102650654341, 6.5645861252665325), 15f))
                    5 -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.49865972369122, 3.889089642954602), 15f))
                    6 -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.984000926031385, 5.9015619098965235), 15f))
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }

    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView!!.onStop()
    }

    private fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.addMarker(MarkerOptions().position(LatLng(52.08955234572531, 5.109965441115019)).title("HQ Utrecht"))
        mMap.addMarker(MarkerOptions().position(LatLng(52.37919726315386, 4.900437200645117)).title("HQ Amsterdam"))
        mMap.addMarker(MarkerOptions().position(LatLng(51.5846112088578, 4.797199300628366)).title("HQ Breda"))
        mMap.addMarker(MarkerOptions().position(LatLng(53.21102650654341, 6.5645861252665325)).title("HQ Groningen"))
        mMap.addMarker(MarkerOptions().position(LatLng(51.49865972369122, 3.889089642954602)).title("HQ Goes"))
        mMap.addMarker(MarkerOptions().position(LatLng(51.984000926031385, 5.9015619098965235)).title("HQ Arnhem"))
    }

    override fun onPause() {
        mMapView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView!!.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _locationBinding = null
    }

    companion object {
        private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    }
}