package com.example.ironathlete.ui.diets

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

class DietsViewModel : ViewModel() {

    private var serverFoodRepository = ServerFoodRepository()
    private var mealList : ArrayList<MealObject> = ArrayList()
    private var ingrendientList = mutableMapOf<String,IngredientObject>()
    private var amountProtein = 0.0
    private var amountCarbs = 0.0
    private var amountFats = 0.0

    private val loadMealsFromServer : MutableLiveData<Boolean> = MutableLiveData()
    val loadMealsFromServerDone: LiveData<Boolean> =loadMealsFromServer

    private val setMealsInServer : MutableLiveData<Boolean> = MutableLiveData()
    val setMealsInServerDone: LiveData<Boolean> =setMealsInServer

    private val ingredientLoaded : MutableLiveData<Boolean> = MutableLiveData()
    val ingredientLoadedDone: LiveData<Boolean> = ingredientLoaded

    private val calculatedAmountFoodComplete : MutableLiveData<ArrayList<MealObject>> = MutableLiveData()
    val calculatedAmountFoodCompleteDone: LiveData<ArrayList<MealObject>> = calculatedAmountFoodComplete

    fun loadMealsFromServer() {
        GlobalScope.launch(Dispatchers.IO){
            val querySnapshot=serverFoodRepository.loadMeals()
            for(document in querySnapshot){
                val mealServer: MealObject = document.toObject<MealObject>()
                mealServer.amountProtein = amountProtein/4
                mealServer.amountCarbs=amountCarbs/4
                mealServer.amountFats = amountFats/4
                mealList.add(mealServer)
            }
            loadMealsFromServer.postValue(true)
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

    fun calculateAmountFood(){
        for(meal in mealList){
            Log.d("AQUI",ingrendientList[meal.proteinId]?.amount.toString())
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
        }
        calculatedAmountFoodComplete.value=mealList
    }

    fun setMealInServer() {

        val meal3 = MealObject(
            mid="",
            amountCarbs=0.0,
            amountFats=0.0,
            amountProtein=0.0,
            image = "https://firebasestorage.googleapis.com/v0/b/ironathlete.appspot.com/o/meals%2FfirstMeal%2Ftajadasaguacatehuevo.jpg?alt=media&token=9262e408-681c-4322-aeec-f5529ca9d8a0",
            carbsId = "kjOZjuY0PmlrXZQIg1hn",
            description = "La vieja confiable: el huevo.",
            proteinId = "3bKsJ1GsacGAmF6kf7zj",
            fatsId = "gASXZaZ9MDsTAugsJrUK",
            ingredients = "Queso al gusto.\nTomate al gusto.\nLechuga al gusto.\nPizca de sal.\nPizca de pimienta.",
            name = "Pan tajado con aguacate y huevo",
            preparation = "Tosta el pan y cubrelo con aguacate y tomate.\nDespués, salpimienta el sándwich.\nEn un sartén antiadherente realiza los huevos revueltos hasta que estén completamente cocidos.\nRetíralos del fuego y colócalos encima del tomate.\nIncorpora el queso, sirve y disfruta de un desayuno saludable y completo."
        )

        val meal2 = MealObject(
            mid="",
            amountCarbs=0.0,
            amountFats=0.0,
            amountProtein=0.0,
            image = "https://firebasestorage.googleapis.com/v0/b/ironathlete.appspot.com/o/meals%2FsecondMeal%2Fcomida2.jpeg?alt=media&token=73d5af85-e49e-4e6e-b69b-ec676ceaf45f",
            carbsId = "kjOZjuY0PmlrXZQIg1hn",
            description = "Recordando a mamá",
            proteinId = "XjjnHlP1J2K4vCh25TV3",
            fatsId = "aXTUZkzXNlelChJ23xIj",
            ingredients = "75ml de cilantro fresco.\n50g de cebolla morada.\n50g de pimiento rojo.\n2 cucharadas de alcaparras.\n2 cucharaditas de zumo de limón.\nSal al gusto.\nPimienta negra molida.",
            name = "Sandwich de atun",
            preparation = "Pica bien todas las verduras y mézclalas en un bol, junto al atún, las alcaparras bien escurridas y la mayonesa.\nAgregale a la mezcla sal y pimienta al gusto.\nTen en cuenta que el atún lleva siempre sal, por lo que es importante probarlo todo antes que pasarse.\nOpcionalmente, puedes añadir también un poco de salsa picante.\nNosotros echamos unas gotas de salsa de Habanero y fue todo un éxito. \n Agrega la mezcla al pan tajado y disfruta."
        )

        val meal1 = MealObject(
            mid="",
            amountCarbs=0.0,
            amountFats=0.0,
            amountProtein=0.0,
            image = "https://firebasestorage.googleapis.com/v0/b/ironathlete.appspot.com/o/meals%2FthirdMeal%2Fcomida3.jpg?alt=media&token=ec29ecab-d01d-46ae-8b49-a89fdd770c19",
            carbsId = "NY1b70K5UMdWYbGjxFY1",
            description = "Sencillo pero nutritivo.",
            proteinId = "cBlueONwS4XQWnIfcFb0",
            fatsId = "gASXZaZ9MDsTAugsJrUK",
            ingredients = "",
            name = "Pechuga con arroz y aguacate",
            preparation = "Cocina el arroz al gusto.\nCocina la pechuga.\nSepara la cascara y el aguacate.\nPon todo en el plato y a disfrutar."
        )

        val meal4 = MealObject(
            mid="",
            amountCarbs=0.0,
            amountFats=0.0,
            amountProtein=0.0,
            image = "https://firebasestorage.googleapis.com/v0/b/ironathlete.appspot.com/o/meals%2FfourthMeal%2Fcomida4.png?alt=media&token=ea7b4c94-9d68-4128-b6b7-f45befdc99b0",
            carbsId = "ZRfOQYxjoMr3NL9aBX5D",
            description = "Internacionaliza tu cocina.",
            proteinId = "4ExaNQMyuEsAudp7RRnb",
            fatsId = "gASXZaZ9MDsTAugsJrUK",
            ingredients = "\n1.5 tazas de hojas de albahaca.\n2 dientes de ajo picado.\nMedia taza de queso parmezano.\nun ocatavo de tazas de piñoes.\n1/4 de taza de aceite de oliva.\n Un sexto de taza de mantequilla.\nUn cuarto de taza de peregil picado.",
            name = "Carne de res al pesto con papas Cambray",
            preparation = "Licúa la albahaca, la mitad de los ajos, el parmesano y los piñones, con aceite de oliva.\nSalpimienta el filete por ambos lados. Por un lado baña con el pesto y enrolla. Sujeta con ayuda de hilo de cocina.\nSella en una sartén o plancha a fuego alto y termina la cocción en el horno durante 30 minutos a 180 ºC. Hierve las papas en agua salada hasta que estén suaves; corta por la mitad y saltea con la mantequilla, el resto del ajo y el perejil, a fuego alto hasta que doren. Sirve acompañando el filete y el aguacate."
        )

        val arrayMealforSet: ArrayList<MealObject> = ArrayList()
        arrayMealforSet.add(meal1)
        arrayMealforSet.add(meal2)
        arrayMealforSet.add(meal3)
        arrayMealforSet.add(meal4)

        GlobalScope.launch(Dispatchers.IO){
            for (i in arrayMealforSet) serverFoodRepository.setMealInServer(i)
            setMealsInServer.postValue(true)
        }

    }


}