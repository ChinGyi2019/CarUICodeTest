package com.chingyi.caruitestapp.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chingyi.caruitestapp.R
import com.chingyi.caruitestapp.data.model.Car
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderImageAdapter: SliderViewAdapter<SliderViewHolder>() {

    var movieList = arrayListOf<Car>()
    override fun getCount() : Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent : ViewGroup?) : SliderViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.slider_item_layout, null)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder : SliderViewHolder? , position : Int) {
        val item = movieList[position]
        viewHolder?.bindView( car = item)
    }
}

class SliderViewHolder(itemView : View) : SliderViewAdapter.ViewHolder(itemView) {

//    private var ivBackDrop : ImageView
//    private var movieTitleTv : TextView
    init {
        super.itemView
//        ivBackDrop = itemView.findViewById(R.id.iv_backDrop)
//        movieTitleTv = itemView.findViewById(R.id.movie_title)



    }


    fun  bindView(car : Car){
    }

}