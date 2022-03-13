package avans.avd.rent_my_car_android_app

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import avans.avd.rent_my_car_android_app.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var light: Sensor
    private lateinit var switchAutoTheme: SwitchCompat
    private lateinit var switchDarkTheme: SwitchCompat
    private val preference = AppPreference(RentMyCarApplication.context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // logic for navigation drawer
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.LoginFragment,
                R.id.HomeFragment,
                R.id.CarListFragment,
                R.id.CarCategoryFragment,
                R.id.MapsFragment,
                R.id.ReservationFragment,
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // locate and add event listeners to nav buttons
        val menuItemAutoTheme = navView.menu.findItem(R.id.switch_auto_theme)
        val menuItemDarkTheme = navView.menu.findItem(R.id.switch_dark_theme)

        this.switchAutoTheme = menuItemAutoTheme.actionView as SwitchCompat
        this.switchDarkTheme = menuItemDarkTheme.actionView as SwitchCompat

        switchAutoTheme.setOnClickListener() {
            this.switchDarkTheme.isEnabled = !this.switchAutoTheme.isChecked

            if (this.switchDarkTheme.isEnabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                this.switchDarkTheme.isChecked = false
            }
        }

        this.switchDarkTheme.setOnCheckedChangeListener { _, value ->
            if (!this.switchAutoTheme.isChecked) {
                when (value) {
                    true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

        // register light sensor event
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)?.let {
            this.light = it
        }
        sensorManager.registerListener(this, this.light, SensorManager.SENSOR_DELAY_NORMAL)

        // reset token on application start
        preference.clearPreferences()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onSensorChanged(event: SensorEvent?) {
//       | Lighting condition | From (lux) |   To (lux)  |
//       |--------------------|------------|-------------|
//       | Pitch Black        |       0    |       10    |
//       | Very Dark          |      11    |       50    |
//       | Dark Indoors       |      51    |      200    |
//       | Dim Indoors        |     201    |      400    |
//       | Normal Indoors     |     401    |     1000    |
//       | Bright Indoors     |    1001    |     5000    |
//       | Dim Outdoors       |    5001    |   10,000    |
//       | Cloudy Outdoors    |  10,001    |   30,000    |
//       | Direct Sunlight    |  30,001    |  100,000    |

        when (event?.sensor?.type) {
            Sensor.TYPE_LIGHT -> {
                if (this.switchAutoTheme.isChecked) {
                    val lux = event?.values[0]

                    if (lux > 30000) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }

                    this.switchDarkTheme.isEnabled = false
                }
            }
        }
    }

    override fun onAccuracyChanged(event: Sensor?, p1: Int) {
        // Not implemented
    }
}
