package com.chingyi.caruitestapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.chingyi.caruitestapp.R
import com.chingyi.caruitestapp.data.model.Car
import com.chingyi.caruitestapp.databinding.FragmentHomeBinding
import com.chingyi.caruitestapp.databinding.FragmentSearchBinding
import com.chingyi.caruitestapp.ui.home.HomeViewModel
import com.chingyi.caruitestapp.util.AnimationAgent
import com.smarteist.autoimageslider.IndicatorView.animation.AnimationManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1 : String? = null
    private var param2 : String? = null

    private var _binding : FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!
    private val carAdapter = CarListAdapter()
    private val animationAgent = AnimationAgent()

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
        // Inflate the layout for this fragment

        _binding = FragmentSearchBinding.inflate(inflater , container , false)
        val root : View = binding.root

        initView()
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.slide_bottom)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView(){
        setUpRecyclerAdapter()
        setUpDummyData()
    }
    private fun setUpRecyclerAdapter(){

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
        }
        binding.carRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext()
            )
            adapter = carAdapter
            setHasFixedSize(true)

        }

        carAdapter.onItemClick = {car->
         Navigation.findNavController(requireView()).navigate(
             R.id.action_searchFragment_to_detailsFragment
         )

        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }


    }


    private fun setUpDummyData(){
        var carList = arrayListOf<Car>()
        carList.add(Car(id = 1, name = "Mazada 3", model = "SMS1000Z",seatCount = 5, distance = "0.5 km away",
        rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 2, name = "Mazada 4", model = "SMS1000Z",seatCount = 5, distance = "10 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 3, name = "Mazada 5", model = "SMS1000Z",seatCount = 5, distance = "0.4 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 4, name = "Mazada 6", model = "SMS1000Z",seatCount = 5, distance = "0.7 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 5, name = "Mazada 7", model = "SMS1000Z",seatCount = 5, distance = "0.3 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 6, name = "Mazada 8", model = "SMS1000Z",seatCount = 5, distance = "0.5 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 7, name = "Mazada 9", model = "SMS1000Z",seatCount = 5, distance = "0.5 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 8, name = "Mazada 10", model = "SMS1000Z",seatCount = 5, distance = "10 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))
        carList.add(Car(id = 9, name = "Mazada 11", model = "SMS1000Z",seatCount = 5, distance = "8 km away",
            rentFeePerHr = "Fr.$3.00/hr",mileageFeePerHr = "$0.40/km"))

        carAdapter.addItem(carList)
        animationAgent.runLayoutAnimation(binding.carRecycler, true)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1 : String , param2 : String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1 , param1)
                    putString(ARG_PARAM2 , param2)
                }
            }
    }
}