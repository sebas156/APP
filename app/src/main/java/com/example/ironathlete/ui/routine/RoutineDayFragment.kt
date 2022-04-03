package com.example.ironathlete.ui.routine

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.databinding.RoutineDayFragmentBinding
import com.example.ironathlete.local.exercise.ExerciseFirebase
import com.example.ironathlete.local.routineDay.RoutineDay
import com.example.ironathlete.server.UserObject
import com.example.ironathlete.ui.main.MainActivity
import com.example.ironathlete.ui.muscle.MuscleAdapter
import com.example.ironathlete.ui.muscle.MuscleFragmentDirections
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RoutineDayFragment : Fragment() {

    companion object {
        fun newInstance() = RoutineDayFragment()
    }

    private lateinit var viewModel: RoutineDayViewModel

    private lateinit var activity: MainActivity
    lateinit var currentUser : UserObject

    private lateinit var routineDayBinding: RoutineDayFragmentBinding
    private lateinit var routineDayViewModel: RoutineDayViewModel
    private lateinit var routineDayAdapter: RoutineDayAdapter

    private lateinit var manager: GridLayoutManager
    private lateinit var decorator: DividerItemDecoration

    val db = Firebase.firestore
    var rutinesRef = db.collection("rutines")
    var exerciseDayRef = db.collection("exercises_day")

    private var exerciseDayIds: ArrayList<String> = ArrayList()
    private var exerciseDayList: ArrayList<RoutineDay> = ArrayList()
    private var currentRoutineDay: RoutineDay = RoutineDay()
    private var routineName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity = getActivity() as MainActivity
        currentUser = activity.getUserObject()

        routineDayBinding = RoutineDayFragmentBinding.inflate(inflater, container, false)
        routineDayViewModel = ViewModelProvider(this)[RoutineDayViewModel::class.java]

        if (currentUser.goToGym == true){
            routineName = "RutinaGym"
        } else {
            routineName = "RutinaCasa"
        }

        rutinesRef
            .whereEqualTo("name", routineName)
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
                                    exerciseDayList.add(currentRoutineDay)
                                }

                                routineDayAdapter.setExercises(exerciseDayList)
                                routineDayBinding.RoutineDaysAvailable.apply{
                                    layoutManager = manager
                                    adapter = routineDayAdapter
                                    addItemDecoration(decorator)
                                    setHasFixedSize(false)
                                }
                            }
                        }
                }
            }

        return routineDayBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager = GridLayoutManager(this@RoutineDayFragment.requireContext(), 2)
        decorator = DividerItemDecoration(this@RoutineDayFragment.requireContext(),manager.orientation)
        routineDayAdapter= RoutineDayAdapter(exerciseDayList) { onItemSelected(it) }
        routineDayBinding.RoutineDaysAvailable.apply{
            layoutManager = manager
            adapter = routineDayAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoutineDayViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun onItemSelected(routineDay: RoutineDay){
        Log.i("selected",routineDay.day.toString())
        findNavController().navigate(RoutineDayFragmentDirections.actionRoutineDayFragmentToMuscleFragment(routineDay))
    }

}