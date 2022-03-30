package com.example.ironathlete.ui.routine

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseCardItemBinding
import com.example.ironathlete.databinding.RoutineDayCardItemBinding
import com.example.ironathlete.local.exercise.ExerciseFirebase
import com.example.ironathlete.local.routineDay.RoutineDay
import com.example.ironathlete.ui.muscle.MuscleAdapter

class RoutineDayAdapter (
    private var routineDayList: ArrayList<RoutineDay>,
    private val onClickListener: (RoutineDay) -> Unit
    ): RecyclerView.Adapter<RoutineDayAdapter.RoutineDayViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineDayAdapter.RoutineDayViewHolder {
        //Tenemos que instanciar una vista.

        val view = LayoutInflater.from(parent.context).inflate(R.layout.routine_day_card_item,parent,false)
        return RoutineDayAdapter.RoutineDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutineDayViewHolder, position: Int) {
        val routineDay = routineDayList[position]
        holder.bind(routineDay,onClickListener)
    }

    override fun getItemCount(): Int = routineDayList.size

    fun setExercises(routineDay: ArrayList<RoutineDay>) {
        this.routineDayList = routineDay
    }

    class RoutineDayViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView
        private val binding = RoutineDayCardItemBinding.bind(itemView)
        fun bind(routineDay: RoutineDay, onClickListener:(RoutineDay) -> Unit){
            with(binding){
                Log.i("id",routineDay.id.toString())
                //cardImage.setImageResource(exercise.image)
                //Picasso.get().load(exercise.image).into(cardImage)
                Glide.with(cardImage.context).load(routineDay.image).into(cardImage)
                cardTitle.text = "Dia " + routineDay.day.toString()
                //cardRep.text = exercise.repetitions.toString() + " Rep"
                //cardWeight.text = exercise.weight.toString() + " Lb"



                //cardDescription.text= "Meal "+exercise.numberMeal.toString()
            }

            itemView.setOnClickListener{
                onClickListener(routineDay)
            }
        }
    }
    }