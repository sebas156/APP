package com.example.ironathlete.ui.diets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ironathlete.R
import com.example.ironathlete.databinding.MealCardItemBinding
import com.example.ironathlete.local.diet.mealItem
import com.example.ironathlete.server.MealObject
import com.squareup.picasso.Picasso

class DietsAdapter (
    private val dietList: ArrayList<MealObject>,
    private val onClickListener:(MealObject) -> Unit
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

    fun appendItems(newList: ArrayList<MealObject>) {
        dietList.clear()
        dietList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dietList.size

    class DietViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView

        private val binding = MealCardItemBinding.bind(itemView)
        fun bind(meal: MealObject, onClickListener:(MealObject) -> Unit){
            with(binding){
                Picasso.get().load(meal.image).into(cardBackground);
                cardTitle.text = meal.name
                cardDescription.text = meal.description
            }

            itemView.setOnClickListener{
                onClickListener(meal)
            }
        }
    }
}