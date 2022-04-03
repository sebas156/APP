package com.example.ironathlete.ui.statistics

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseCardItemBinding
import com.example.ironathlete.local.exercise.ExerciseFirebase

class ExerciseStatisticsAdapter (
    private var exerciseList: ArrayList<ExerciseFirebase>,
    private val onClickListener:(ExerciseFirebase) -> Unit
    ): RecyclerView.Adapter<ExerciseStatisticsAdapter.ExerciseStatisticsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseStatisticsViewHolder {
        //Tenemos que instanciar una vista.

        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_card_item,parent,false)
        return ExerciseStatisticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseStatisticsViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.bind(exercise,onClickListener)
    }

    override fun getItemCount(): Int = exerciseList.size

    fun setExercises(exercises: ArrayList<ExerciseFirebase>) {
        this.exerciseList = exercises
    }

    class ExerciseStatisticsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView
        private val binding = ExerciseCardItemBinding.bind(itemView)
        fun bind(exercise: ExerciseFirebase, onClickListener:(ExerciseFirebase) -> Unit){
            with(binding){
                Log.i("id",exercise.id.toString())
                //cardImage.setImageResource(exercise.image)
                //Picasso.get().load(exercise.image).into(cardImage)
                Glide.with(cardImage.context).load(exercise.image).into(cardImage)
                cardTitle.text = exercise.name
                cardRep.text = ""
                cardWeight.text = ""
                checkBox.visibility = View.INVISIBLE
                imageView7.visibility = View.GONE
                imageView8.visibility = View.GONE

                //cardDescription.text= "Meal "+exercise.numberMeal.toString()
            }

            itemView.setOnClickListener{
                onClickListener(exercise)
            }
        }
    }
}