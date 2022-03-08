package com.example.ironathlete.ui.muscle

import com.example.ironathlete.local.exercise.Exercise
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentMuscleBinding
import java.sql.Types.NULL

//import com.example.ironathlete.ui.muscle.MuscleFragmentDirections

class MuscleFragment : Fragment() {

    private lateinit var muscleBinding: FragmentMuscleBinding
    private lateinit var muscleViewModel: MuscleViewModel
    private lateinit var muscleAdapter: MuscleAdapter
    private var exerciseList: ArrayList<Exercise> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        muscleBinding = FragmentMuscleBinding.inflate(inflater,container,false)
        muscleViewModel = ViewModelProvider(this)[MuscleViewModel::class.java]
        return muscleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseList=ArrayList()

        exerciseList.add(
            Exercise(
                NULL,
                "Skipping",
                R.drawable.skipping,
                15,
                10f,
                "Cuerpo debería estar en posición diagonal con respecto al suelo. Activa el core y asegúrate de que el cuerpo forma una línea recta de la cabeza a los pies.",
                R.drawable.skipping
            )
        )
        exerciseList.add(
            Exercise(
                NULL,
                "Abdomen",
                R.drawable.abdomen,
                25,
                8f,
                "Eleva el torso hacia las rodillas sin levantar la espalda completamente del suelo ni levantar los pies. Ejerce fuerza en los abdominales, evitando forzar otros músculos.",
                R.drawable.skipping
            )
        )
        exerciseList.add(
            Exercise(
                NULL,
                "Peso Muerto",
                R.drawable.pexels_photo_841130,
                10,
                10f,
                "Con la espalda completamente recta, flexiona ligeramente las rodillas.Comienza a descender el cuerpo con la espalda recta e intentando que los muslos queden paralelos al cuerpo y los pies y la parte inferior de las piernas en ángulo recto.",
                R.drawable.skipping
            )
        )
        exerciseList.add(
            Exercise(
                NULL,
                "Rotacion de Tronco",
                R.drawable.abdomen,
                25,
                6f,
                "Las rotaciones abdominales se realizan girando la parte inferior del tronco, de este modo lo que debemos tener presente es mantener las piernas inmóviles, al igual que la parte superior",
                R.drawable.skipping
            )
        )
        exerciseList.add(
            Exercise(
                NULL,
                "Espalda",
                R.drawable.pexels_photo_841130,
                30,
                10f,
                "Cuerpo debería estar en posición diagonal con respecto al suelo. Activa el core y asegúrate de que el cuerpo forma una línea recta de la cabeza a los pies.",
                R.drawable.skipping
            )
        )
        exerciseList.add(
            Exercise(
                NULL,
                "Sentadilla",
                R.drawable.sentadilla,
                20,
                12f,
                "Cuerpo debería estar en posición diagonal con respecto al suelo. Activa el core y asegúrate de que el cuerpo forma una línea recta de la cabeza a los pies.",
                R.drawable.skipping
            )
        )

        val manager = LinearLayoutManager(this@MuscleFragment.requireContext())
        val decorator = DividerItemDecoration(this@MuscleFragment.requireContext(),manager.orientation)
        muscleAdapter= MuscleAdapter(exerciseList) { onItemSelected(it) }
        muscleBinding.ExercisesAvailable.apply{
            layoutManager = manager
            adapter = muscleAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }
    }

    private fun onItemSelected(exercise: Exercise){
        findNavController().navigate(MuscleFragmentDirections.actionMuscleFragmentToExerciseFragment(exercise))
    }
}