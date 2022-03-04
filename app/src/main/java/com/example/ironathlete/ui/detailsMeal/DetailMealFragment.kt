package com.example.ironathlete.ui.detailsMeal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentDetailMealBinding

class DetailMealFragment : Fragment() {

    companion object {
        fun newInstance() = DetailMealFragment()
    }

    private lateinit var detailMealViewModel: DetailMealViewModel
    private lateinit var detailMealBinding: FragmentDetailMealBinding
    private val args : DetailMealFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailMealBinding= FragmentDetailMealBinding.inflate(inflater,container, false)
        detailMealViewModel = ViewModelProvider(this)[DetailMealViewModel::class.java]
        return detailMealBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val informationMeal = args.mealInformation

        with(detailMealBinding){
            titlePreparation.text = informationMeal.nameMeal
            foodImageView.setImageResource(informationMeal.background)
        }
    }

}