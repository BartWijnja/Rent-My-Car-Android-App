package avans.avd.rent_my_car_android_app.ui.car_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.databinding.FragmentCarListBinding
import avans.avd.rent_my_car_android_app.ui.adapter.CarListAdapter
import avans.avd.rent_my_car_android_app.ui.adapter.CustomAdapter
import avans.avd.rent_my_car_android_app.viewmodel.CarListViewModel
import avans.avd.rent_my_car_android_app.viewmodel.CarViewModel


class CarListFragment : Fragment() {
    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!
//    private val carViewModel: CarViewModel by lazy {
//        ViewModelProvider(this)[CarViewModel::class.java]
//    }

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

        // getting the recyclerview by its id
        val recyclerview = binding.recyclerviewCarlist

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(activity)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<CarListViewModel>()

        // This loop will create 20 Views containing
        data.add(CarListViewModel("brand", "brandType", "model", "40.0000", "FCEV"))
        data.add(CarListViewModel("brand", "brandType", "model", "40.0000", "FCEV"))
        data.add(CarListViewModel("brand", "brandType", "model", "40.0000", "FCEV"))
        data.add(CarListViewModel("brand", "brandType", "model", "40.0000", "FCEV"))
        data.add(CarListViewModel("brand", "brandType", "model", "40.0000", "FCEV"))
        data.add(CarListViewModel("brand", "brandType", "model", "40.0000", "FCEV"))
        data.add(CarListViewModel("brand", "brandType", "model", "40.0000", "FCEV"))


//         This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

//         Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}