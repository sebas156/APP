package com.example.ironathlete.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    
    private lateinit var homeViewModel : HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container,false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeBinding){
           infoImageView.setImageResource(R.drawable.infoperfil)
            stadisticsImageView.setImageResource(R.drawable.estadistica)

            infoUserCardView.setOnClickListener{
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToInfoUserFragment())
            }

            stadisticsCardView.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToExerciseStatisticsFragment())
            }
        }

    }


}