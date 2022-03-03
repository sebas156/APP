package com.example.ironathlete.ui.diets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        dietsAdapter= DietsAdapter(dietsList)
        dietsBinding.DietsAvailables.apply{
            layoutManager = LinearLayoutManager(this@DietsFragment.requireContext())
            adapter=dietsAdapter
            setHasFixedSize(false)
        }
    }
}