package com.example.ironathlete.ui.comunity

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    private lateinit var communityBinding: FragmentCommunityBinding
    private lateinit var communityViewModel: CommunityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communityBinding= FragmentCommunityBinding.inflate(inflater,container,false)
        communityViewModel = ViewModelProvider(this)[CommunityViewModel::class.java]
        return communityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}