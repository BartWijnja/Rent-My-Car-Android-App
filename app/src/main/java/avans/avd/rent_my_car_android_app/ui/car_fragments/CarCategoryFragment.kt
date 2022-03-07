package avans.avd.rent_my_car_android_app.ui.car_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.databinding.FragmentCarCatergoryBinding
import avans.avd.rent_my_car_android_app.viewmodel.CarViewModel

class CarCategoryFragment : Fragment() {
    private var _binding: FragmentCarCatergoryBinding? = null
    private val binding get() = _binding!!
    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
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

        carViewModel.carResult.observe(viewLifecycleOwner) { cars ->
            if (cars == null) {
                Toast.makeText(activity,
                    R.string.network_call_failed,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val listView = binding.listCarCategory

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                    cars.map {
                        it.brand + " | " + it.brandType + " | " + it.model + " | €" + it.price
                    })

                listView.adapter = adapter
            }
        }

        binding.spCarType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> carViewModel.findByCarType("BEV")
                    1 -> carViewModel.findByCarType("ICE")
                    2 -> carViewModel.findByCarType("FCEV")
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }

        carViewModel.findByCarType("BEV")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}