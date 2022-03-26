package com.example.ironathlete.ui.muscle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseCardItemBinding
//import com.example.ironathlete.local.exercise.Exercise
import com.example.ironathlete.local.exercise.ExerciseFirebase
//import com.squareup.picasso.Picasso

class MuscleAdapter (
    private var exerciseList: ArrayList<ExerciseFirebase>,
    private val onClickListener:(ExerciseFirebase) -> Unit
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

        fun setExercises(exercises: ArrayList<ExerciseFirebase>) {
            this.exerciseList = exercises
        }

        class MuscleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView
            private val binding = ExerciseCardItemBinding.bind(itemView)
            fun bind(exercise: ExerciseFirebase, onClickListener:(ExerciseFirebase) -> Unit){
                with(binding){
                    Log.i("id",exercise.id)
                    //cardImage.setImageResource(exercise.image)
                    //Picasso.get().load(exercise.image).into(cardImage)
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