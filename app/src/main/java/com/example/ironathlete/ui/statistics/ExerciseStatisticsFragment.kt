package com.example.ironathlete.ui.statistics

import android.content.ContentValues
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseStatisticsFragmentBinding
import com.example.ironathlete.databinding.FragmentMuscleBinding
import com.example.ironathlete.local.exercise.ExerciseFirebase
import com.example.ironathlete.local.routineDay.RoutineDay
import com.example.ironathlete.ui.muscle.MuscleAdapter
import com.example.ironathlete.ui.muscle.MuscleFragmentDirections
import com.example.ironathlete.ui.muscle.MuscleViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

private lateinit var exerciseStatisticsBinding: ExerciseStatisticsFragmentBinding
private lateinit var exerciseStatisticsViewModel: ExerciseStatisticsViewModel
private lateinit var exerciseStatisticsAdapter: ExerciseStatisticsAdapter
private lateinit var manager: LinearLayoutManager
private lateinit var decorator: DividerItemDecoration

private var exerciseFList: ArrayList<ExerciseFirebase> = ArrayList()
private var exerciseDayIds: ArrayList<String> = ArrayList()
private var exerciseIds: ArrayList<String> = ArrayList()
private var currentRoutineDay: RoutineDay = RoutineDay()

val db = Firebase.firestore
var rutinesRef = db.collection("rutines")
var exerciseDayRef = db.collection("exercises_day")
var exercisesRef = db.collection("exercises")

class ExerciseStatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = ExerciseStatisticsFragment()
    }

    private lateinit var viewModel: ExerciseStatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exerciseStatisticsBinding = ExerciseStatisticsFragmentBinding.inflate(inflater,container,false)
        exerciseStatisticsViewModel = ViewModelProvider(this)[ExerciseStatisticsViewModel::class.java]


        rutinesRef
            .whereEqualTo("name", "RutinaGym")
            .get()
            .addOnSuccessListener { documents ->
                run {
                    for (rutine in documents) {
                        Log.i(
                            "rutine",
                            (rutine.data.get("exercisesDayList") as Map<String, Boolean>).keys.toString()
                        )
                        exerciseDayIds =
                            ArrayList((rutine.data.get("exercisesDayList") as Map<String, Boolean>).keys)
                    }

                    exerciseDayRef
                        .whereIn("id", exerciseDayIds)
                        .get()
                        .addOnSuccessListener { documents ->
                            run {
                                for (routineDay in documents) {

                                    currentRoutineDay = routineDay.toObject<RoutineDay>()
                                    //currentRoutineDay.exercisesList = routineDay.data.get("exercisesList") as Map<String, Boolean>
                                    Log.i("exerciseDay", currentRoutineDay.toString())
                                    for (exercise in ArrayList<String>(currentRoutineDay.exercisesList!!.keys)) {
                                        exerciseIds.add(exercise)
                                    }
                                }

                                exercisesRef
                                    //.whereIn("id", exerciseIds)
                                    .addSnapshotListener { value, error -> run {

                                        if (error != null) {
                                            Log.w(ContentValues.TAG, "Listen failed.", error)
                                            return@addSnapshotListener
                                        }

                                        for (exercise in value!!) {
                                            if (exerciseIds.contains(exercise.id)) {
                                                exerciseFList.add(exercise.toObject<ExerciseFirebase>())
                                                Log.i(
                                                    "exercise",
                                                    "${exerciseFList[0].id} = ${exerciseFList[0].name}"
                                                )

                                                exerciseStatisticsAdapter.setExercises(exerciseFList)
                                                exerciseStatisticsBinding.ExercisesAvailable.apply {
                                                    layoutManager = manager
                                                    adapter = exerciseStatisticsAdapter
                                                    addItemDecoration(decorator)
                                                    setHasFixedSize(false)
                                                }
                                            }

                                        }
                                    }}

                                }
                            }
                        }
                }

        /*exercisesRef
            //.whereIn("id", routineDay.exercisesDayList!!.keys as ArrayList<String>)
            .addSnapshotListener { value, error -> run {

                if (error != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", error)
                    return@addSnapshotListener
                }

                for (exercise in value!!) {
                    if (ArrayList(routineDay.exercisesList!!.keys).contains(exercise.id)) {
                        exerciseFList.add(exercise.toObject<ExerciseFirebase>())
                        Log.i("exercise", "${exerciseFList[0].id} = ${exerciseFList[0].name}")

                        muscleAdapter.setExercises(exerciseFList)
                        muscleBinding.ExercisesAvailable.apply{
                            layoutManager = manager
                            adapter = muscleAdapter
                            addItemDecoration(decorator)
                            setHasFixedSize(false)
                        }
                    }

                }
            }}*/

        manager = LinearLayoutManager(this@ExerciseStatisticsFragment.requireContext())
        decorator = DividerItemDecoration(this@ExerciseStatisticsFragment.requireContext(),manager.orientation)
        exerciseStatisticsAdapter= ExerciseStatisticsAdapter(exerciseFList) { onItemSelected(it) }
        exerciseStatisticsBinding.ExercisesAvailable.apply{
            layoutManager = manager
            adapter = exerciseStatisticsAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }

        return exerciseStatisticsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    private fun onItemSelected(exercise: ExerciseFirebase){
        Log.i("selected",exercise.name.toString())
        findNavController().navigate(ExerciseStatisticsFragmentDirections.actionExerciseStatisticsFragmentToStatisticsFragment(exercise))
    }

}