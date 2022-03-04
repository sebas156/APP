package com.example.ironathlete.ui.diets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ironathlete.R
import com.example.ironathlete.databinding.MealCardItemBinding
import com.example.ironathlete.mealItem

class DietsAdapter (
    private val dietList: ArrayList<mealItem>,
    private val onClickListener:(mealItem) -> Unit
    ): RecyclerView.Adapter<DietsAdapter.DietViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        //Tenemos que instanciar una vista.

        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_card_item,parent,false)
        return DietViewHolder(view)
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        val meal = dietList[position]
        holder.bind(meal,onClickListener)
    }

    override fun getItemCount(): Int = dietList.size

    class DietViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView
        private val binding = MealCardItemBinding.bind(itemView)
        fun bind(meal: mealItem, onClickListener:(mealItem) -> Unit){
            with(binding){
                cardBackground.setImageResource(meal.background)
                cardTitle.text = meal.nameMeal
                cardDescription.text= "Meal "+meal.numberMeal.toString()
            }

            itemView.setOnClickListener{
                onClickListener(meal)
            }
        }
    }
}