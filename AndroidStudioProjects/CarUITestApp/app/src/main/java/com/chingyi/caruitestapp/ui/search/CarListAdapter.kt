package com.chingyi.caruitestapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.chingyi.caruitestapp.R
import com.chingyi.caruitestapp.data.model.Car
import kotlinx.android.synthetic.main.car_list_item.view.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class CarListAdapter : RecyclerView.Adapter<CarListAdapter.CarItemListHolder>(){

    private val cars = ArrayList<Car>()
    var onItemClick: ((Car) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): CarItemListHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.car_list_item,parent,false)

        return CarItemListHolder(view)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarItemListHolder, position: Int) {
        holder.bindData(cars[position])

    }
    fun addItem(subjects : ArrayList<Car>){
        if(this.cars.isEmpty()){
            this.cars.addAll(subjects)
        }
        else{
            this.cars.clear()
            this.cars.addAll(subjects)
        }
        notifyDataSetChanged()
    }

    inner class CarItemListHolder constructor(@NonNull itemView: View):
        RecyclerView.ViewHolder(itemView){
        init {
            super.itemView
        }

        fun bindData(car: Car){

            itemView.carName.text = car.name
            itemView.carModel.text = car.model
            itemView.distance.text = car.distance
            itemView.seatCount.text  = String.format("%d Seater",car.seatCount)
            itemView.rental_fee.text = car.rentFeePerHr
            itemView.milage_fee.text = car.mileageFeePerHr
            itemView.setOnClickListener {
                onItemClick?.invoke(car)
            }

        }

    }

}