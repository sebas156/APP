package com.example.ironathlete.ui.diets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentDietsBinding

class DietsFragment : Fragment() {

    private lateinit var dietsBinding: FragmentDietsBinding
    private lateinit var dietsViewModel: DietsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dietsBinding = FragmentDietsBinding.inflate(inflater,container,false)
        dietsViewModel = ViewModelProvider(this)[DietsViewModel::class.java]
        return dietsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}