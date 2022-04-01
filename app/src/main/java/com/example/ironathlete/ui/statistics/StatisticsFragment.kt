package com.example.ironathlete.ui.statistics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ironathlete.R
import com.example.ironathlete.databinding.ExerciseStatisticsFragmentBinding
import com.example.ironathlete.databinding.StatisticsFragmentBinding
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

private lateinit var statisticsBinding: StatisticsFragmentBinding


class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statisticsBinding = StatisticsFragmentBinding.inflate(inflater,container,false)

        var points = arrayOf(DataPoint(0.0, 1.0), DataPoint(1.0, 1.0), DataPoint(2.0, 1.0))

        statisticsBinding.graph.addSeries(LineGraphSeries<DataPoint>(points))

        return statisticsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}