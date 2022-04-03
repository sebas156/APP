package com.example.ironathlete.ui.comunity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.Community
import com.example.ironathlete.databinding.FragmentCommunityBinding
import com.example.ironathlete.server.ComunityObject
import com.example.ironathlete.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CommunityFragment : Fragment(), dialogSquare.FinishedDialogSquare{

    private lateinit var communityBinding: FragmentCommunityBinding
    private lateinit var communityViewModel: CommunityViewModel
    private lateinit var communityAdapter: CommunityAdapter
    private var communityList: ArrayList<ComunityObject> = ArrayList()
    private lateinit var activity: MainActivity

    private lateinit var dialog: dialogSquare

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity = getActivity() as MainActivity
        communityBinding = FragmentCommunityBinding.inflate(inflater, container, false)
        communityViewModel = ViewModelProvider(this)[CommunityViewModel::class.java]
        return communityBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communityViewModel.updatedPublicationDone.observe(viewLifecycleOwner){
            result ->
            updatedPublicationDoneSuscribe(result)
        }

        communityList = ArrayList()

        communityBinding.addForoButton.setOnClickListener {
            dialog= dialogSquare(requireContext(),this)
        }

        val manager = LinearLayoutManager(this@CommunityFragment.requireContext())
        val decorator =
            DividerItemDecoration(this@CommunityFragment.requireContext(), manager.orientation)
        communityAdapter = CommunityAdapter(communityList) {}/* onItemSelected(it) }*/
        communityBinding.CommunityAvailable.apply {
            layoutManager = manager
            adapter = communityAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }
    }

    private fun updatedPublicationDoneSuscribe(result: ComunityObject?) {
        if (result != null) {
            communityList.add(result)
        }
    }


    private fun onItemSelected(community: Community) {
        //findNavController().navigate(MuscleFragmentDirections.actionMuscleFragmentToExerciseFragment(community))
    }

    override fun ResultDialogSquare(result: String) {
        if(result.isEmpty()) Toast.makeText(requireContext(),"El contenido que tiene que publicar debe ser diferente de vacio",Toast.LENGTH_SHORT).show()
        else{
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date = Date()
            val fecha: String = dateFormat.format(date)
            val nameUser = activity.getName()
            val ageUser = activity.getAge()
            val objetiveUser = activity.getObjetive()
            communityViewModel.publicateContent(result,fecha,nameUser,ageUser,objetiveUser)
        }
    }

}