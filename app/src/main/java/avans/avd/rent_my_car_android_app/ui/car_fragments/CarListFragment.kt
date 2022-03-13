package avans.avd.rent_my_car_android_app.ui.car_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import avans.avd.rent_my_car_android_app.AppPreference
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.RentMyCarApplication
import avans.avd.rent_my_car_android_app.databinding.FragmentCarListBinding
import avans.avd.rent_my_car_android_app.network.response.CarDisplayResponse
import avans.avd.rent_my_car_android_app.viewmodel.CarDisplayViewModel
import avans.avd.rent_my_car_android_app.viewmodel.CarViewModel
import avans.avd.rent_my_car_android_app.viewmodel.ReservationViewModel

class CarListFragment : Fragment() {
    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!
    private val preference = AppPreference(RentMyCarApplication.context)
    private val carDisplayViewModel: CarDisplayViewModel by lazy {
        ViewModelProvider(this)[CarDisplayViewModel::class.java]
    }
    private val reservationViewModel: ReservationViewModel by lazy {
        ViewModelProvider(this)[ReservationViewModel::class.java]
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

        carDisplayViewModel.carDisplayResult.observe(viewLifecycleOwner) { carDisplays ->
            if (carDisplays == null) {
                Toast.makeText(activity, R.string.network_call_failed, Toast.LENGTH_SHORT).show()
            } else {
                val userId = preference.getUserId()
                reservationViewModel.findByUser(userId.toLong())

                reservationViewModel.reservationResult.observe(viewLifecycleOwner) { reservations ->
                    val listView = binding.listviewCarlist

                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                        carDisplays.map {
                            it.car.brand + " | " + it.car.brandType + " | " + it.car.model + " | €" + it.car.price
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

                    binding.listviewCarlist.setOnItemClickListener { adapterView, view, i, l ->
                        val carDisplayId = carDisplays[i].id
                        val bundle = bundleOf("carDisplayId" to carDisplayId)
                        findNavController().navigate(R.id.action_CarListFragment_to_ReservationFragment, bundle)
                    }
                }
            }
        }

        val locationId = preference.getLocationId()
        carDisplayViewModel.findAllByLocation(locationId.toLong())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
