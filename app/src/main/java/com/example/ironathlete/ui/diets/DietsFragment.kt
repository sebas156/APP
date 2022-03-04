package com.example.ironathlete.ui.diets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ironathlete.R
import com.example.ironathlete.databinding.FragmentDietsBinding
import com.example.ironathlete.mealItem

class DietsFragment : Fragment() {

    private lateinit var dietsBinding: FragmentDietsBinding
    private lateinit var dietsViewModel: DietsViewModel
    private lateinit var dietsAdapter: DietsAdapter
    private var dietsList: ArrayList<mealItem> = ArrayList()

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

        dietsList.add(
            mealItem(
                R.drawable.burguer,
                "Hamburguesa Colombiana",
                1
            )
        )
        dietsList.add(
            mealItem(
                R.drawable.riceandchicken,
                "Arroz y pollo",
                2
            )
        )

        val manager = LinearLayoutManager(this@DietsFragment.requireContext())
        val decorator = DividerItemDecoration(this@DietsFragment.requireContext(),manager.orientation)
        dietsAdapter= DietsAdapter(dietsList) { onItemSelected(it) }
        dietsBinding.DietsAvailables.apply{
            layoutManager = manager
            adapter=dietsAdapter
            addItemDecoration(decorator)
            setHasFixedSize(false)
        }
    }

    private fun onItemSelected(meal: mealItem){
        findNavController().navigate(DietsFragmentDirections.actionDietsFragmentToDetailMealFragment(meal))
    }
}