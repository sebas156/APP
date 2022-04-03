package com.example.ironathlete.ui.exerciseDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseFragmentBinding

class ExerciseFragment : Fragment() {

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var exerciseBinding: ExerciseFragmentBinding
    private val args : ExerciseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exerciseBinding= ExerciseFragmentBinding.inflate(inflater,container, false)
        exerciseViewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]
        return exerciseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val informationExercise = args.exerciseInformation

        with(exerciseBinding){
            exerciseName.text = informationExercise.name
            Glide.with(exerciseImage.context).load(informationExercise.gif).into(exerciseImage)
            //exerciseImage.setImageResource(informationExercise.gif)
            descriptionText.text = informationExercise.description

        }
    }

}