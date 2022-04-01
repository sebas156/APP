package com.example.ironathlete.ui.comunity

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.Community
import com.example.ironathlete.local.exercise.Exercise
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentCommunityBinding
import com.example.ironathlete.local.user.userAccount
import com.example.ironathlete.ui.muscle.MuscleAdapter
import com.example.ironathlete.ui.muscle.MuscleFragmentDirections

class CommunityFragment : Fragment() {

    private lateinit var communityBinding: FragmentCommunityBinding
    private lateinit var communityViewModel: CommunityViewModel
    private lateinit var communityAdapter: CommunityAdapter
    private var communityList: ArrayList<Community> = ArrayList()

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

        communityList=ArrayList()


        val manager = LinearLayoutManager(this@CommunityFragment.requireContext())
        val decorator = DividerItemDecoration(this@CommunityFragment.requireContext(),manager.orientation)
        communityAdapter= CommunityAdapter(communityList) {}/* onItemSelected(it) }*/
        communityBinding.CommunityAvailable.apply{
            layoutManager = manager
            adapter = communityAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }
    }

    private fun onItemSelected(community: Community){
        //findNavController().navigate(MuscleFragmentDirections.actionMuscleFragmentToExerciseFragment(community))
    }

}