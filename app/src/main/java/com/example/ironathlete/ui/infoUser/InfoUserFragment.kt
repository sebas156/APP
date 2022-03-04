package com.example.ironathlete.ui.infoUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentInfoUserBinding

class InfoUserFragment : Fragment() {

    companion object {
        fun newInstance() = InfoUserFragment()
    }

    private lateinit var infoUserViewModel: InfoUserViewModel
    private lateinit var infoUserBinding: FragmentInfoUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoUserBinding= FragmentInfoUserBinding.inflate(inflater,container,false)
        infoUserViewModel = ViewModelProvider(this)[InfoUserViewModel::class.java]
        return infoUserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}