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

        communityList.add(
            Community(
                "¿Que dias van al Gym?",
                "Me gustaria conocer personas para ir a entrenar,soy ingeniero civil y me gusta el crossfit",
                120f,
                10,
                userAccount(0,"Dario@gmail.com" ,"*****")
            )
        )
        communityList.add(
            Community(
                "¿Les gusta los deportes?",
                "Me gustaria conocer personas para ir a entrenar,soy ingeniero civil y me gusta el crossfit",
                125f,
                34,
                userAccount(0,"ZoeDoria2015@gmail.com" ,"*****")
            )
        )
        communityList.add(
            Community(
                "¿Les gusta Hacer Pierna?",
                "Me gustaria conocer personas para ir a entrenar,soy ingeniero civil y me gusta el crossfit",
                170f,
                50,
                userAccount(0,"DavidBetanur@gmail.com" ,"*****")
            )
        )

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