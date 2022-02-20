package avans.avd.rent_my_car_android_app.ui.location

import android.Manifest
import android.content.pm.PackageManager

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import avans.avd.rent_my_car_android_app.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : AppCompatActivity(), OnMapReadyCallback {
    private var mMapView: MapView? = null
    private lateinit var mMap: GoogleMap
    private val LOCATION_PERMISSION_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_location)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView = findViewById<View>(R.id.map_view_location) as MapView
        mMapView!!.onCreate(mapViewBundle)
        mMapView!!.getMapAsync(this)

        sp_locations.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        mMapView!!.onSaveInstanceState(mapViewBundle)
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationAccess()

        mMap.addMarker(MarkerOptions().position(LatLng(52.08955234572531, 5.109965441115019)).title("HQ Utrecht"))
        mMap.addMarker(MarkerOptions().position(LatLng(52.37919726315386, 4.900437200645117)).title("HQ Amsterdam"))
        mMap.addMarker(MarkerOptions().position(LatLng(51.5846112088578, 4.797199300628366)).title("HQ Breda"))
        mMap.addMarker(MarkerOptions().position(LatLng(53.21102650654341, 6.5645861252665325)).title("HQ Groningen"))
        mMap.addMarker(MarkerOptions().position(LatLng(51.49865972369122, 3.889089642954602)).title("HQ Goes"))
        mMap.addMarker(MarkerOptions().position(LatLng(51.984000926031385, 5.9015619098965235)).title("HQ Arnhem"))
    }

    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        } else ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                mMap.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "User has not granted location access permission", Toast.LENGTH_LONG).show()
                finish()
            }
        }
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

    companion object {
        private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    }
}