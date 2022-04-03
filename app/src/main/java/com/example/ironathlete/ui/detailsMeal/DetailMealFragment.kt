package com.example.ironathlete.ui.detailsMeal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentDetailMealBinding
import com.example.ironathlete.server.MealObject
import com.squareup.picasso.Picasso

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

        detailMealViewModel.ingredientLoadedDone.observe(viewLifecycleOwner){
            onIngredientLoadedDoneSuscribe()
        }

        detailMealViewModel.calculatedAmountFoodCompleteDone.observe(viewLifecycleOwner){
                result->
            onCalculatedAmountFoodCompleteDoneSuscribe(result)
        }

        detailMealViewModel.getIngredients()
    }

    private fun onCalculatedAmountFoodCompleteDoneSuscribe(result: String?) {

        val informationMeal = args.mealInformation
        informationMeal.ingredients=result
        with(detailMealBinding){
            titlePreparation.text = informationMeal.name
            Picasso.get().load(informationMeal.image).into(foodImageView)
            ingredientsAmountsTextView.text = informationMeal.ingredients
            preparationTextView.text = informationMeal.preparation
        }
    }

    private fun onIngredientLoadedDoneSuscribe() {
        detailMealViewModel.calculateAmountFood(args.mealInformation)
    }

}