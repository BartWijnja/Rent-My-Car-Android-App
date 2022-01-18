package avans.avd.rent_my_car_android_app.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import avans.avd.rent_my_car_android_app.databinding.ActivityCarBinding
import avans.avd.rent_my_car_android_app.viewmodel.CarViewModel

class CarActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val model: CarViewModel by viewModels()
        model.carResponse.observe(this) {
            binding.result.text = model.carResponse.value
        }

        binding.get.setOnClickListener {
            model.getCarItems()
        }

        binding.delete.setOnClickListener {
            model.deleteCarItem(1)
        }

//        // todo: introduce Moshi converter
//        binding.post.setOnClickListener {
//            val carItem = CarItem()
//            model.postCarItem(carItem)
//        }
    }
}