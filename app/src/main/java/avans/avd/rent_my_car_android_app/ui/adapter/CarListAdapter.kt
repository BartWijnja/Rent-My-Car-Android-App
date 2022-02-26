package avans.avd.rent_my_car_android_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.network.response.CarResponse

class CarListAdapter(
        var carResponse: List<CarResponse>
    ) : RecyclerView.Adapter<CarListAdapter.CarListViewHolder>() {

    inner class CarListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
//        holder.itemView.apply {
//            brand.text = carResponse[position].brand
//            brand_type.text = carResponse[position].brandType
//            model.text = carResponse[position].model
//            price.text = carResponse[position].price.toString()
//            car_type.text = carResponse[position].carType
//        }
    }

    override fun getItemCount(): Int {
        return carResponse.size
    }
}