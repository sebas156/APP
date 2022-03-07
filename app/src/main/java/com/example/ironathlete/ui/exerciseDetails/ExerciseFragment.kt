package com.example.ironathlete.ui.exerciseDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
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
        return inflater.inflate(R.layout.exercise_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val informationExercise = args.exerciseInformation

        with(exerciseBinding){
            exerciseName.text = informationExercise.name
            exerciseImage.setImageResource(informationExercise.gif)
            exerciseDescription.text = informationExercise.description

        }
    }

}