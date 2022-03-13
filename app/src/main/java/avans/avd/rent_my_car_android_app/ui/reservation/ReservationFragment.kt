package avans.avd.rent_my_car_android_app.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import avans.avd.rent_my_car_android_app.AppPreference
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.RentMyCarApplication
import avans.avd.rent_my_car_android_app.databinding.FragmentReservationBinding
import avans.avd.rent_my_car_android_app.enums.ReservationStatus
import avans.avd.rent_my_car_android_app.model.CarDisplay
import avans.avd.rent_my_car_android_app.model.Location
import avans.avd.rent_my_car_android_app.model.Reservation
import avans.avd.rent_my_car_android_app.model.User
import avans.avd.rent_my_car_android_app.viewmodel.CarDisplayViewModel
import avans.avd.rent_my_car_android_app.viewmodel.ReservationViewModel
import avans.avd.rent_my_car_android_app.viewmodel.UserViewModel

class ReservationFragment: Fragment() {
    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!
    private val preference = AppPreference(RentMyCarApplication.context)
    private val carDisplayViewModel: CarDisplayViewModel by lazy {
        ViewModelProvider(this)[CarDisplayViewModel::class.java]
    }
    private val reservationViewModel: ReservationViewModel by lazy {
        ViewModelProvider(this)[ReservationViewModel::class.java]
    }
    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val carDisplayId = ReservationFragmentArgs.fromBundle(it).carDisplayId
            val userId = preference.getUserId()

            carDisplayViewModel.findById(carDisplayId)

            carDisplayViewModel.singleCarDisplayResult.observe(viewLifecycleOwner) { carDisplay ->
                binding.reservationTitle.text = carDisplay.car.brand + " " + carDisplay.car.brandType + " " + carDisplay.car.model
                binding.reservationSeats.text = getString(R.string.seats) + ": " + carDisplay.car.personSpace
                binding.reservationEngine.text = getString(R.string.engine) + ": " + carDisplay.car.carType
                binding.reservationPricePerDay.text = getString(R.string.price_per_day) + ": €" + carDisplay.car.price
                binding.reservationTotalPrice.text = getString(R.string.total_price) + ": €" + (binding.daysSlider.value * carDisplay.car.price)

                binding.daysSlider.addOnChangeListener { slider, value, fromUser ->
                    binding.reservationTotalPrice.text = getString(R.string.total_price) + ": €" + (binding.daysSlider.value * carDisplay.car.price)
                }

                reservationViewModel.findByUser(userId.toLong())
                reservationViewModel.reservationResult.observe(viewLifecycleOwner) { reservations ->
                    var isAlreadyReserved = false

                    for (reservation in reservations) {
                        println(reservation.carDisplay.id)
                        println(carDisplay.id)
                        if (reservation.carDisplay.id.equals(carDisplay.id)) {
                            isAlreadyReserved = true
                            break
                        }
                    }

                    println("isAlreadyReserved")
                    println(isAlreadyReserved)

                    if (isAlreadyReserved) {
                        binding.reserveButton.isEnabled = false
                        binding.reservationAlreadyDone.text = getString(R.string.reservation_already_done)
                    } else {
                        binding.reserveButton.isEnabled = true
                        binding.reserveButton.setOnClickListener {
                            val totalPrice = binding.daysSlider.value * carDisplay.car.price
                            val status = ReservationStatus.PENDING
                            val daysReserved = binding.daysSlider.value.toInt()
                            val localCarDisplay = CarDisplay(
                                id = carDisplay.id,
                                total = carDisplay.total,
                                available = carDisplay.available,
                                location = carDisplay.location,
                                car = carDisplay.car,
                            )

                            println(totalPrice)
                            println(status)
                            println(daysReserved)
                            println(localCarDisplay)

                            userViewModel.find(userId.toLong())
                            userViewModel.userLiveData.observe(viewLifecycleOwner) { user ->
                                println(user)
                                user?.let { nonNullUser ->
                                    println(nonNullUser)
                                    val localUser = User(
                                        id = nonNullUser.id,
                                        firstName = nonNullUser.firstName,
                                        lastName = nonNullUser.lastName,
                                        username = nonNullUser.username,
                                        phoneNumber = nonNullUser.phoneNumber,
                                        email = nonNullUser.email,
                                        password = nonNullUser.password,
                                        iban = nonNullUser.iban,
                                        userRole = nonNullUser.userRole,
                                        location = nonNullUser.location,
                                        enabled = nonNullUser.enabled,
                                        locked = nonNullUser.locked,
                                        createdAt = nonNullUser.createdAt,
                                    )

                                    val reservation = Reservation(
                                        totalPrice = totalPrice,
                                        status = status,
                                        daysReserved = daysReserved,
                                        carDisplay = localCarDisplay,
                                        user = localUser
                                    )

                                    reservationViewModel.create(reservation)
                                    Thread.sleep(300) // wait for request to finish
                                    findNavController().navigate(R.id.action_ReservationFragment_to_HomeFragment)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
