package com.chingyi.caruitestapp.ui.details

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chingyi.caruitestapp.R
import com.chingyi.caruitestapp.data.model.Car
import com.chingyi.caruitestapp.databinding.FragmentDetailsBinding
import com.chingyi.caruitestapp.databinding.FragmentSearchBinding
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment() {

    private var param1 : String? = null
    private var param2 : String? = null
    private var _binding : FragmentDetailsBinding? = null
    private val sliderAdapter = SliderImageAdapter()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View? {
        _binding = FragmentDetailsBinding.inflate(inflater , container , false)
        val root : View = binding.root

        initView()
        return root
    }

    private fun initView() {
        setUpSliderAdapter()

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpSliderAdapter(){
        binding.imageSlider.setSliderAdapter(sliderAdapter)
        binding.imageSlider.apply {

            setIndicatorAnimation(IndicatorAnimationType.SCALE)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            indicatorSelectedColor = resources.getColor(R.color.color_primary)
            indicatorUnselectedColor = Color.GRAY
            scrollTimeInSec = 4 //set scroll delay in seconds :
            startAutoCycle()
        }

        var carList = arrayListOf<Car>()
        carList.add(
            Car(id = 1, name = "Mazada 3", model = "SMS1000Z",seatCount = 5, distance = "0.5 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km")
        )
        carList.add(
            Car(id = 2, name = "Mazada 4", model = "SMS1000Z",seatCount = 5, distance = "10 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km")
        )
        carList.add(
            Car(id = 3, name = "Mazada 5", model = "SMS1000Z",seatCount = 5, distance = "0.4 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km")
        )
        sliderAdapter.movieList = carList
        sliderAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1 : String , param2 : String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1 , param1)
                    putString(ARG_PARAM2 , param2)
                }
            }
    }
}