package com.example.ironathlete.ui.diets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentDietsBinding
import com.example.ironathlete.local.diet.mealItem
import com.example.ironathlete.server.MealObject
import com.example.ironathlete.ui.main.MainActivity
import java.sql.Types.NULL

class DietsFragment : Fragment() {

    private lateinit var dietsBinding: FragmentDietsBinding
    private lateinit var dietsViewModel: DietsViewModel
    private lateinit var dietsAdapter: DietsAdapter
    private lateinit var activity: MainActivity
    //private var dietsList: ArrayList<mealItem> = ArrayList() Room
    private var mealsList: ArrayList<MealObject> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity = getActivity() as MainActivity
        dietsBinding = FragmentDietsBinding.inflate(inflater,container,false)
        dietsViewModel = ViewModelProvider(this)[DietsViewModel::class.java]
        return dietsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dietsViewModel.loadMealsFromServerDone.observe(viewLifecycleOwner){
            result ->
            onLoadMealsFromServerDoneSuscribe(result)
        }

        activity.getProteinRequirenment()?.let { dietsViewModel.setProteinAmount(it) }
        activity.getCarbsRequirenment()?.let { dietsViewModel.setCarbsAmount(it) }
        activity.getFatsRequirenment()?.let { dietsViewModel.setFatsAmount(it) }

        dietsViewModel.loadMealsFromServer()

        val manager = LinearLayoutManager(this@DietsFragment.requireContext())
        val decorator = DividerItemDecoration(this@DietsFragment.requireContext(),manager.orientation)
        dietsAdapter= DietsAdapter(mealsList) { onItemSelected(it) }
        dietsBinding.DietsAvailables.apply{
            layoutManager = manager
            adapter=dietsAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }
    }

    private fun onLoadMealsFromServerDoneSuscribe(result: ArrayList<MealObject>) {
        mealsList = result
        dietsAdapter.appendItems(mealsList)
    }

    private fun onItemSelected(meal: MealObject){
        findNavController().navigate(DietsFragmentDirections.actionDietsFragmentToDetailMealFragment(meal))
    }


}


