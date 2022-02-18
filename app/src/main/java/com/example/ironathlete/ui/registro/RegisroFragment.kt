package com.example.ironathlete.ui.registro

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ironathlete.R

class RegisroFragment : Fragment() {

    companion object {
        fun newInstance() = RegisroFragment()
    }

    private lateinit var viewModel: RegisroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisroViewModel::class.java)
        // TODO: Use the ViewModel
    }

}