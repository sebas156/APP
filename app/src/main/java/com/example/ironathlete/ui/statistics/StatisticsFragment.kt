package com.example.ironathlete.ui.statistics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseStatisticsFragmentBinding
import com.example.ironathlete.databinding.StatisticsFragmentBinding
import com.example.ironathlete.local.exercise.ExerciseFirebase
import com.example.ironathlete.ui.muscle.MuscleFragmentArgs
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class StatisticsFragment : Fragment() {

    private var statisticsRepetitionsList: ArrayList<Long> = ArrayList()
    private var statisticsWeightList: ArrayList<Double> = ArrayList()
    private var weightPoints: ArrayList<DataPoint> = ArrayList()
    private var repetitionPoints: ArrayList<DataPoint> = ArrayList()
    private var seriesWeight: LineGraphSeries<DataPoint> = LineGraphSeries()
    private var seriesRepetitions: LineGraphSeries<DataPoint> = LineGraphSeries()

    private lateinit var statisticsBinding: StatisticsFragmentBinding

    private val args : StatisticsFragmentArgs by navArgs()

    val db = Firebase.firestore
    var exerciseStatistics = db.collection("excercise_statistics")

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statisticsBinding = StatisticsFragmentBinding.inflate(inflater,container,false)

        val exercise = args.exercise

        exerciseStatistics
            .whereEqualTo("excercise_id", exercise.id)
            .get()
            .addOnSuccessListener { documents ->
                run {
                    for(statistics in documents) {
                        statisticsRepetitionsList.add(statistics.data.get("repetitions") as Long)
                        statisticsWeightList.add(statistics.data.get("weight").toString().toDouble())
                    }
                    Log.i("stat1",statisticsRepetitionsList.toString())
                    Log.i("stat1",statisticsWeightList.toString())

                    var i: Double = 0.0
                    for (weight in statisticsWeightList) {
                        weightPoints.add(DataPoint(i, weight))
                        i += 1
                        Log.i("weight", weightPoints[(i-1).toInt()].toString())
                    }
                    statisticsBinding.graphWeight.viewport.setMinY(0.0)
                    seriesWeight = LineGraphSeries<DataPoint>(weightPoints.toTypedArray())
                    seriesWeight.setTitle("Day vs Weight")
                    statisticsBinding.graphWeight.addSeries(seriesWeight)
                    statisticsBinding.graphWeight.getLegendRenderer().setVisible(true);

                    i = 0.0
                    for (repetitions in statisticsRepetitionsList) {
                        repetitionPoints.add(DataPoint(i, repetitions.toDouble()))
                        i += 1
                    }
                    statisticsBinding.graphRepetitions.viewport.setMinY(0.0)
                    seriesRepetitions = LineGraphSeries<DataPoint>(repetitionPoints.toTypedArray())
                    seriesRepetitions.setTitle("Day vs Repetitions");
                    statisticsBinding.graphRepetitions.addSeries(seriesRepetitions)
                    statisticsBinding.graphRepetitions.getLegendRenderer().setVisible(true);

                }
            }

        //var points = arrayOf(DataPoint(0.0, 1.0), DataPoint(1.0, 1.0), DataPoint(2.0, 1.0))

        //statisticsBinding.graph.addSeries(LineGraphSeries<DataPoint>(points))

        return statisticsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}