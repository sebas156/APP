package com.example.ironathlete.ui.diets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ironathlete.server.MealObject
import com.example.ironathlete.server.ServerRepositories.ServerFoodRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DietsViewModel : ViewModel() {

    private var serverFoodRepository = ServerFoodRepository()
    private var mealList : ArrayList<MealObject> = ArrayList()
    private var amountProtein = 0.0
    private var amountCarbs = 0.0
    private var amountFats = 0.0

    private val loadMealsFromServer : MutableLiveData<ArrayList<MealObject>> = MutableLiveData()
    val loadMealsFromServerDone: LiveData<ArrayList<MealObject>> =loadMealsFromServer

    fun loadMealsFromServer() {
        GlobalScope.launch(Dispatchers.IO){
            val querySnapshot=serverFoodRepository.loadMeals()
            for(document in querySnapshot){
                val mealServer: MealObject = document.toObject<MealObject>()
                mealServer.amountProtein = amountProtein/5
                mealServer.amountCarbs=amountCarbs/5
                mealServer.amountFats = amountFats/5
                mealList.add(mealServer)
            }
            loadMealsFromServer.postValue(mealList)
        }
    }

    fun setProteinAmount(protein: Double){
        amountProtein = protein
    }
    fun setCarbsAmount(carbs: Double){
        amountCarbs = carbs
    }
    fun setFatsAmount(fats: Double){
        amountFats = fats
    }


}