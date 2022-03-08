package com.example.ironathlete.ui.muscle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseCardItemBinding
import com.example.ironathlete.local.exercise.Exercise

class MuscleAdapter (
    private val exerciseList: ArrayList<Exercise>,
    private val onClickListener:(Exercise) -> Unit
    ): RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
            //Tenemos que instanciar una vista.

            val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_card_item,parent,false)
            return MuscleViewHolder(view)
        }

        override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
            val exercise = exerciseList[position]
            holder.bind(exercise,onClickListener)
        }

        override fun getItemCount(): Int = exerciseList.size

        class MuscleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView
            private val binding = ExerciseCardItemBinding.bind(itemView)
            fun bind(exercise: Exercise, onClickListener:(Exercise) -> Unit){
                with(binding){
                    cardImage.setImageResource(exercise.image)
                    cardTitle.text = exercise.name
                    cardRep.text = exercise.repetitions.toString() + " Rep"
                    cardWeight.text = exercise.weight.toString() + " Lb"



                    //cardDescription.text= "Meal "+exercise.numberMeal.toString()
                }

                itemView.setOnClickListener{
                    onClickListener(exercise)
                }
            }
        }
}