package avans.avd.rent_my_car_android_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.databinding.FragmentHomeBinding
import avans.avd.rent_my_car_android_app.viewmodel.CarViewModel
import avans.avd.rent_my_car_android_app.viewmodel.UserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }
    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
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

        binding.buttonHome.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_LoginFragment)
        }

        binding.buttonHome.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_LoginFragment)
        }

        binding.buttonGetAll.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_CarListFragment)
        }

        binding.getByType.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_CarCategoryFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
