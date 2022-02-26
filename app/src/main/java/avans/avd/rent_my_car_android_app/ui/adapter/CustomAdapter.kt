package avans.avd.rent_my_car_android_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.viewmodel.CarListViewModel

class CustomAdapter(private val mList: List<CarListViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val carListViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.brand.text = carListViewModel.brand
        holder.brandType.text = carListViewModel.brandType
        holder.model.text = carListViewModel.model
        holder.price.text = carListViewModel.price.toString()
        holder.carType.text = carListViewModel.carType
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val brand: TextView = itemView.findViewById(R.id.brand)
        val brandType: TextView = itemView.findViewById(R.id.brand_type)
        val model: TextView = itemView.findViewById(R.id.model)
        val price: TextView = itemView.findViewById(R.id.price)
        val carType: TextView = itemView.findViewById(R.id.car_type)
    }
}