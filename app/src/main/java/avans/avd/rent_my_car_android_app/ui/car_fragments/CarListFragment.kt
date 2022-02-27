package avans.avd.rent_my_car_android_app.ui.car_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.databinding.FragmentCarListBinding
import avans.avd.rent_my_car_android_app.ui.adapter.CustomAdapter
import avans.avd.rent_my_car_android_app.viewmodel.CarViewModel

class CarListFragment : Fragment() {
    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!
    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel.carResult.observe(viewLifecycleOwner) { cars ->
            if (cars == null) {
                Toast.makeText(activity, R.string.network_call_failed, Toast.LENGTH_SHORT).show()
            } else {
                // getting the recyclerview by its id
                val recyclerview = binding.recyclerviewCarlist

                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(activity)

                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(cars)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
            }
        }

        carViewModel.findAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
