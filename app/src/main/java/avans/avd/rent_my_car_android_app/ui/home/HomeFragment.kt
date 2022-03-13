package avans.avd.rent_my_car_android_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import avans.avd.rent_my_car_android_app.AppPreference
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.RentMyCarApplication
import avans.avd.rent_my_car_android_app.databinding.FragmentHomeBinding
import avans.avd.rent_my_car_android_app.enums.ReservationStatus
import avans.avd.rent_my_car_android_app.viewmodel.LoginViewModel
import avans.avd.rent_my_car_android_app.viewmodel.ReservationViewModel
import avans.avd.rent_my_car_android_app.viewmodel.factory.LoginViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val preference = AppPreference(RentMyCarApplication.context)
    private lateinit var loginViewModel: LoginViewModel
    private val reservationViewModel: ReservationViewModel by lazy {
        ViewModelProvider(this)[ReservationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        binding.buttonHome.setOnClickListener {
            loginViewModel.logout()
            findNavController().navigate(R.id.action_HomeFragment_to_LoginFragment)
        }

        binding.buttonGetAll.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_CarListFragment)
        }

        binding.getByType.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_CarCategoryFragment)
        }
        binding.buttonMaps.setOnClickListener{
            findNavController().navigate(R.id.action_HomeFragment_to_MapsFragment)
        }

        val locationId = preference.getLocationId()

        if (locationId == 0) {
            binding.buttonGetAll.isEnabled = false
            binding.getByType.isEnabled = false
            binding.locationWarning.text = "No location selected"
        }

        reservationViewModel.reservationResult.observe(viewLifecycleOwner) { reservations ->
            if (reservations == null) {
                Toast.makeText(activity, R.string.network_call_failed, Toast.LENGTH_SHORT).show()
            } else {
                val listView = binding.listviewRentedCars

                val validReservations = reservations.filter {
                    it.status != ReservationStatus.EXPIRED
                }

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                    validReservations.map {
                        it.carDisplay.car?.brand + " | " +
                                it.carDisplay.car?.brandType + " | " +
                                it.carDisplay.car?.model + " | €" +
                                it.carDisplay.car?.price + " | Location: " +
                                it.carDisplay.location?.title + " | Days: " +
                                it.daysReserved + " | Status: " +
                                it.status
                    })

                listView.adapter = adapter
            }
        }

        val userId = preference.getUserId()
        reservationViewModel.findByUser(userId.toLong())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
