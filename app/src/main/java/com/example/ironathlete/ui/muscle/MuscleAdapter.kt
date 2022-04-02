package com.example.ironathlete.ui.muscle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
                    Log.i("id",exercise.id.toString())
                    //cardImage.setImageResource(exercise.image)
                    //Picasso.get().load(exercise.image).into(cardImage)
                    Glide.with(cardImage.context).load(exercise.image).into(cardImage)
                    cardTitle.text = exercise.name
                    cardRep.text = exercise.repetitions.toString() + " Rep"
                    cardWeight.text = exercise.weight.toString() + " Lb"

                    checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
                        if (cardRep.visibility == View.VISIBLE){
                            cardRep.visibility = View.INVISIBLE
                            repSpinner.visibility = View.VISIBLE
                        } else {
                            cardRep.visibility = View.VISIBLE
                            repSpinner.visibility = View.INVISIBLE
                        }
                        if (cardWeight.visibility == View.VISIBLE) {
                            cardWeight.visibility = View.INVISIBLE
                            weightSpinner.visibility = View.VISIBLE
                        } else {
                            cardWeight.visibility = View.VISIBLE
                            weightSpinner.visibility = View.INVISIBLE
                        }

                    }

                    var weightList = ArrayList<Float>()
                    for (i in 0 until 200) {
                        weightList.add(i.toFloat()/2)
                    }

                    weightSpinner.adapter = ArrayAdapter<Float>(weightSpinner.context, android.R.layout.simple_spinner_item, weightList)

                    var repsList = ArrayList<Int>()
                    for (i in 0 until 50) {
                        repsList.add(i)
                    }

                    repSpinner.adapter = ArrayAdapter<Int>(repSpinner.context, android.R.layout.simple_spinner_item, repsList)

                    //var weightAdapter: ArrayAdapter<String> = ArrayAdapter(, weightSpinner)

                    //cardDescription.text= "Meal "+exercise.numberMeal.toString()
                }

                itemView.setOnClickListener{
                    onClickListener(exercise)
                }
            }
        }

}