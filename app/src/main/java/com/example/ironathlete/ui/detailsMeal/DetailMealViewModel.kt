package com.example.ironathlete.ui.detailsMeal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.server.IngredientObject
import com.example.ironathlete.server.MealObject
import com.example.ironathlete.server.ServerRepositories.ServerFoodRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMealViewModel : ViewModel() {
    private var serverFoodRepository = ServerFoodRepository()

    private var ingrendientList = mutableMapOf<String,IngredientObject>()

    private val ingredientLoaded : MutableLiveData<Boolean> = MutableLiveData()
    val ingredientLoadedDone: LiveData<Boolean> = ingredientLoaded

    private val calculatedAmountFoodComplete : MutableLiveData<String?> = MutableLiveData()
    val calculatedAmountFoodCompleteDone: LiveData<String?> = calculatedAmountFoodComplete

    fun getIngredients(){
        GlobalScope.launch(Dispatchers.IO){
            val allsIngredient = serverFoodRepository.getIngredient()

            for (ingredient in allsIngredient){
                val objectIngrendient = ingredient.toObject<IngredientObject>()
                ingrendientList[objectIngrendient.foodId.toString()] = objectIngrendient
            }
            ingredientLoaded.postValue(true)
        }
    }

    fun calculateAmountFood(meal: MealObject){

        var grProtein = ingrendientList[meal.proteinId]?.amount!! * meal.amountProtein!! / ingrendientList[meal.proteinId]?.intake!!
        var grCarbs = ingrendientList[meal.carbsId]?.amount!! * meal.amountCarbs!! / ingrendientList[meal.carbsId]?.intake!!
        var grFats = ingrendientList[meal.fatsId]?.amount!! * meal.amountFats!! / ingrendientList[meal.fatsId]?.intake!!
        var aux = " gr de "
        grProtein = String.format("%.2f",grProtein).toDouble()
        grCarbs = String.format("%.2f",grCarbs).toDouble()
        grFats = String.format("%.2f",grFats).toDouble()

        aux =
           if(ingrendientList[meal.carbsId]?.name == "tajada, pan tajado integral" || ingrendientList[meal.proteinId]?.name == "Huevo AA") " unidades de "
           else " gr de "

        meal.ingredients = grProtein.toString()+aux+ingrendientList[meal.proteinId]?.name+". \n"+grCarbs.toString()+aux+ingrendientList[meal.carbsId]?.name+". \n"+grFats.toString()+aux+ingrendientList[meal.fatsId]?.name+". \n"+meal.ingredients

        calculatedAmountFoodComplete.value=meal.ingredients
    }



}