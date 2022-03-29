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

        val informationMeal = args.mealInformation

        with(detailMealBinding){
            titlePreparation.text = informationMeal.name
            Picasso.get().load(informationMeal.image).into(foodImageView)
            ingredientsAmountsTextView.text = informationMeal.ingredients
            preparationTextView.text = informationMeal.preparation
            Log.d("MealObject",informationMeal.mid.toString())
            Log.d("MealObject",informationMeal.amountCarbs.toString())
            Log.d("MealObject",informationMeal.amountFats.toString())
            Log.d("MealObject",informationMeal.amountProtein.toString())
            Log.d("MealObject",informationMeal.carbsId.toString())
            Log.d("MealObject",informationMeal.description.toString())
            Log.d("MealObject",informationMeal.proteinId.toString())
            Log.d("MealObject",informationMeal.fatsId.toString())
            Log.d("MealObject",informationMeal.image.toString())
            Log.d("MealObject",informationMeal.ingredients.toString())
            Log.d("MealObject",informationMeal.name.toString())
            Log.d("MealObject",informationMeal.preparation.toString())
        }
    }

}