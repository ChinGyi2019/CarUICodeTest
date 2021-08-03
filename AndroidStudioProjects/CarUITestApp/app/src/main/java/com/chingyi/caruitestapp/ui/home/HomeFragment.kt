package com.chingyi.caruitestapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.chingyi.caruitestapp.R
import com.chingyi.caruitestapp.databinding.FragmentHomeBinding
import com.chingyi.caruitestapp.util.AnimationAgent

class HomeFragment : Fragment() {

    private lateinit var homeViewModel : HomeViewModel
    private var _binding : FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    private val animationAgent = AnimationAgent()

    override fun onCreateView(
        inflater : LayoutInflater ,
        container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater , container , false)
        val root : View = binding.root

        initView()
        return root
    }

    private fun initView(){
        animationAgent.shake( binding.flaotingActionBtn, true)
        animationAgent.shake( binding.flaotingActionBtn2, true)


        binding.goBtn.setOnClickListener {view->
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_searchFragment,null,null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}