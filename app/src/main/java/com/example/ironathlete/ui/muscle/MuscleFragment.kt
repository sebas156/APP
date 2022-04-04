package com.example.ironathlete.ui.muscle

import android.content.ContentValues.TAG
//import com.example.ironathlete.local.exercise.Exercise
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.size
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentMuscleBinding
import com.example.ironathlete.local.exercise.ExerciseFirebase
import com.example.ironathlete.ui.exerciseDetails.ExerciseFragmentArgs
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.example.ironathlete.databinding.RoutineDayFragmentBinding
import com.example.ironathlete.local.statistics.ExerciseStatistics
import com.example.ironathlete.server.UserObject
import com.example.ironathlete.ui.main.MainActivity
import java.sql.Types.NULL

//import com.example.ironathlete.ui.muscle.MuscleFragmentDirections

class MuscleFragment : Fragment() {

    private lateinit var muscleBinding: FragmentMuscleBinding
    private lateinit var muscleViewModel: MuscleViewModel
    private lateinit var muscleAdapter: MuscleAdapter
    private lateinit var manager: LinearLayoutManager
    private lateinit var decorator: DividerItemDecoration
    private lateinit var itemHolder: MuscleAdapter.MuscleViewHolder

    //private var exerciseList: ArrayList<Exercise> = ArrayList()
    private var exerciseFList: ArrayList<ExerciseFirebase> = ArrayList()
    private var exerciseDayIds: ArrayList<String> = ArrayList()
    private var exerciseIds: ArrayList<String> = ArrayList()

    private val args : MuscleFragmentArgs by navArgs()
    var maxDay: Long = 0

    val db = Firebase.firestore
    var rutinesRef = db.collection("rutines")
    var exerciseDayRef = db.collection("exercises_day")
    var exercisesRef = db.collection("exercises")
    var statisticsRef = db.collection("excercise_statistics")

    var exerciseWeight: Double = 0.0
    var exerciseReps: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        muscleBinding = FragmentMuscleBinding.inflate(inflater,container,false)
        muscleViewModel = ViewModelProvider(this)[MuscleViewModel::class.java]

        val routineDay = args.routineDay

        Log.i("query result", routineDay.toString())

        exercisesRef
           //.whereIn("id", routineDay.exercisesDayList!!.keys as ArrayList<String>)
            .addSnapshotListener { value, error -> run {

                if (error != null) {
                    Log.w(TAG, "Listen failed.", error)
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
            }}

        //rutinesRef.document("GXVeOpe4E6MbTtBIgI2A").get()
        /*exercisesRef.get()
            .addOnSuccessListener { result ->
                for (rutine in result) {

                    exerciseFList.add(rutine.toObject<ExerciseFirebase>())
                    ejemplo = rutine.toObject<ExerciseFirebase>()
                    Log.i("rutine", "${exerciseFList[0].id} = ${exerciseFList[0].name}")
                }
            }*/

        /*var rutineChildEventListener = object: ChildEventListener {

        }*/
        /*rutinesRef
            .whereEqualTo("name", "RutinaGym")
            .get()
            .addOnSuccessListener { documents -> run {
                for (rutine in documents) {
                    Log.i("rutine", (rutine.data.get("exercisesDayList") as Map<String, Boolean>).keys.toString())
                    exerciseDayIds = ArrayList((rutine.data.get("exercisesDayList") as Map<String, Boolean>).keys)
                }

                exerciseDayRef
                    //.whereIn("exercisesList", mapOf(exerciseDayIds to ))
                    .get()
                    .addOnSuccessListener { documents -> run {
                        for(exerciseDay in documents) {
                            if (exerciseDayIds.contains(exerciseDay.id)) {
                                Log.i(
                                    "exerciseDay",
                                    (exerciseDay.data.get("exercisesList") as Map<String, Boolean>).keys.toString()
                                )
                                for(exerciseId in ArrayList((exerciseDay.data.get("exercisesList") as Map<String, Boolean>).keys)) {
                                    exerciseIds.add(exerciseId)
                                }
                                //exerciseIds =
                                    //(exerciseDay.data.get("exercisesList") as Map<String, Boolean>).keys as ArrayList<String>
                            }
                        }

                        exercisesRef
                            //.whereIn("id", exerciseIds)
                            .addSnapshotListener { value, error -> run {

                                if (error != null) {
                                    Log.w(TAG, "Listen failed.", error)
                                    return@addSnapshotListener
                                }

                                for (exercise in value!!) {
                                    if (exerciseIds.contains(exercise.id)) {
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
                            }}
                    } }
            } }*/

        //exerciseList=ArrayList()

        /*exercisesRef
            .whereIn("id", exerciseIds)
            .addSnapshotListener { value, error -> run {

            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener
            }

            for (exercise in value!!) {
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
        }}*/

        return muscleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*exerciseList.add(
            Exercise(
                NULL,
                "Skipping",
                R.drawable.skipping,
                15,
                10f,
                "Cuerpo debería estar en posición diagonal con respecto al suelo. Activa el core y asegúrate de que el cuerpo forma una línea recta de la cabeza a los pies.",
                R.drawable.exercise
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
                R.drawable.exercise
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
                R.drawable.exercise
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
                R.drawable.exercise
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
                R.drawable.exercise
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
                R.drawable.exercise
            )
        )*/


        //rutinesRef.document("GXVeOpe4E6MbTtBIgI2A").get()
        /*exercisesRef.get()
            .addOnSuccessListener { result ->
                for (rutine in result) {

                    exerciseFList.add(rutine.toObject<ExerciseFirebase>())
                    ejemplo = rutine.toObject<ExerciseFirebase>()
                    Log.i("rutine", "${exerciseFList[0].id} = ${exerciseFList[0].name}")
                }
            }*/


        manager = LinearLayoutManager(this@MuscleFragment.requireContext())
        decorator = DividerItemDecoration(this@MuscleFragment.requireContext(),manager.orientation)
        muscleAdapter= MuscleAdapter(exerciseFList) { onItemSelected(it) }
        muscleBinding.ExercisesAvailable.apply{
            layoutManager = manager
            adapter = muscleAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }

        muscleBinding.saveButton.setOnClickListener {
            for (i in 0 until muscleBinding.ExercisesAvailable.size) {
                //if muscleBinding.
                itemHolder = muscleBinding.ExercisesAvailable.findViewHolderForAdapterPosition(i) as MuscleAdapter.MuscleViewHolder
                var weightAvailable = itemHolder.itemView.findViewById<Spinner>(R.id.weight_spinner).visibility
                var repsAvailable = itemHolder.itemView.findViewById<Spinner>(R.id.rep_spinner).visibility

                if (weightAvailable == View.VISIBLE && repsAvailable == View.VISIBLE) {

                    exerciseWeight =
                        (itemHolder.itemView.findViewById<Spinner>(R.id.weight_spinner).selectedItem as Float).toDouble()
                    exerciseReps =
                        itemHolder.itemView.findViewById<Spinner>(R.id.rep_spinner).selectedItem as Int

                    maxDay = 0

                    Log.i("maxDay", maxDay.toString())

                    statisticsRef
                        .get()
                        .addOnSuccessListener { documents ->
                            run {
                                for (stat in documents) {
                                    if (stat.data.get("excercise_id") == exerciseFList.get(i).id){
                                        Log.i("day", stat.data.get("day").toString())
                                        if (stat.data.get("day") as Long > maxDay) {
                                            maxDay = stat.data.get("day") as Long
                                        }
                                        Log.i("maxDay", maxDay.toString())
                                    }
                                }
                                maxDay += 1
                                Log.i("maxDay", maxDay.toString())
                            }

                            var newDoc = statisticsRef.document()

                            var exerciseStatistics: ExerciseStatistics = ExerciseStatistics(
                                id = newDoc.id,
                                excercise_id = exerciseFList.get(i).id,
                                excercise_name = exerciseFList.get(i).name,
                                day = maxDay.toInt(),
                                weight = exerciseWeight,
                                repetitions = exerciseReps
                            )
                            Log.i("object is",exerciseStatistics.toString())
                            newDoc.set(exerciseStatistics)
                            Toast.makeText(context, "Se han guardado los cambios del día!", Toast.LENGTH_SHORT).show()
                        }


                    Log.i("here1", exerciseReps.toString())
                    Log.i("here2", exerciseWeight.toString())

                }

            }
        }
    }

    override fun onResume() {
        super.onResume()

        //exerciseList=ArrayList()

        /*exercisesRef.addSnapshotListener { value, error -> run {

            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener
            }

            for (exercise in value!!) {
                exerciseFList.add(exercise.toObject<ExerciseFirebase>())
                Log.i("rutine", "${exerciseFList[0].id} = ${exerciseFList[0].name}")

                muscleAdapter.setExercises(exerciseFList)
                muscleBinding.ExercisesAvailable.apply{
                    layoutManager = manager
                    adapter = muscleAdapter
                    addItemDecoration(decorator)
                    setHasFixedSize(false)
                }
            }
        }}*/
    }

    override fun onPause() {
        super.onPause()

        exercisesRef = db.collection("rutines")
    }

    private fun onItemSelected(exercise: ExerciseFirebase){
        Log.i("selected",exercise.name.toString())
        findNavController().navigate(MuscleFragmentDirections.actionMuscleFragmentToExerciseFragment(exercise))
    }
}