package avans.avd.rent_my_car_android_app.ui.car_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import avans.avd.rent_my_car_android_app.AppPreference
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.RentMyCarApplication
import avans.avd.rent_my_car_android_app.databinding.FragmentCarCatergoryBinding
import avans.avd.rent_my_car_android_app.enums.CarType
import avans.avd.rent_my_car_android_app.viewmodel.CarDisplayViewModel
import avans.avd.rent_my_car_android_app.viewmodel.CarViewModel

class CarCategoryFragment : Fragment() {
    private var _binding: FragmentCarCatergoryBinding? = null
    private val binding get() = _binding!!
    private val preference = AppPreference(RentMyCarApplication.context)
    private val carDisplayViewModel: CarDisplayViewModel by lazy {
        ViewModelProvider(this)[CarDisplayViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarCatergoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carDisplayViewModel.carDisplayResult.observe(viewLifecycleOwner) { carDisplays ->
            if (carDisplays == null) {
                Toast.makeText(activity, R.string.network_call_failed, Toast.LENGTH_SHORT).show()
            } else {
                val listView = binding.listCarCategory

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                    carDisplays.map {
                        it.car.brand + " | " + it.car.brandType + " | " + it.car.model + " | €" + it.car.price
                    })

                listView.adapter = adapter

                binding.listCarCategory.setOnItemClickListener { adapterView, view, i, l ->
                    val carDisplayId = carDisplays[i].id
                    val bundle = bundleOf("carDisplayId" to carDisplayId)
                    findNavController().navigate(R.id.action_CarCategoryFragment_to_ReservationFragment, bundle)
                }
            }
        }

        val locationId = preference.getLocationId()

        binding.spCarType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> carDisplayViewModel.findByDisplayWithType(locationId.toLong(), CarType.BEV)
                    1 -> carDisplayViewModel.findByDisplayWithType(locationId.toLong(), CarType.ICE)
                    2 -> carDisplayViewModel.findByDisplayWithType(locationId.toLong(), CarType.FCEV)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }

        carDisplayViewModel.findByDisplayWithType(locationId.toLong(), CarType.BEV)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}