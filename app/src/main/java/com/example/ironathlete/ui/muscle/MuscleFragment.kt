package com.example.ironathlete.ui.muscle

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentMuscleBinding

class MuscleFragment : Fragment() {

    private lateinit var muscleBinding: FragmentMuscleBinding
    private lateinit var viewModel: MuscleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        muscleBinding = FragmentMuscleBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[MuscleViewModel::class.java]
        return muscleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}