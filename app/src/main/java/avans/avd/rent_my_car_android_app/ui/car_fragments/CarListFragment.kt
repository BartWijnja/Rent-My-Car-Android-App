package avans.avd.rent_my_car_android_app.ui.car_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
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
                val listView = binding.listviewCarlist

//                // this creates a vertical layout Manager
//                listView.layoutManager = LinearLayoutManager(activity)

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                    cars.map {
                        it.brand + " | " + it.brandType + " | " + it.model + " | €" + it.price
                    })

                listView.adapter = adapter

                binding.svCarByName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        //Performs search when user hit the search button on the keyboard
//                if (bestCities.contains(p0)) {
//                    adapter.filter.filter(p0)
//                } else {
//                    Toast.makeText(this@MainActivity, "No match found", Toast.LENGTH_SHORT).show()
//                }
                        return false
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        //Start filtering the list as user start entering the characters
                        adapter.filter.filter(p0)
                        return false
                    }
                })

//                // This will pass the ArrayList to our Adapter
//                val adapter = CustomAdapter(cars)
//
//                // Setting the Adapter with the recyclerview
//                recyclerview.adapter = adapter

            }
        }

        carViewModel.findAll()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
