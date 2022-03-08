package com.example.ironathlete.ui.comunity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ironathlete.Community
import com.example.ironathlete.Exercise
import com.example.ironathlete.R
import com.example.ironathlete.databinding.CommunityCardItemBinding
import com.example.ironathlete.databinding.ExerciseCardItemBinding

class CommunityAdapter (
    private val communityList: ArrayList<Community>,
    private val onClickListener:(Community) -> Unit
    ): RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
            //Tenemos que instanciar una vista.

            val view = LayoutInflater.from(parent.context).inflate(R.layout.community_card_item,parent,false)
            return CommunityViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
            val community = communityList[position]
            holder.bind(community,onClickListener)
        }

        override fun getItemCount(): Int = communityList.size

        class CommunityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView
            private val binding = CommunityCardItemBinding.bind(itemView)
            fun bind(community: Community, onClickListener:(Community) -> Unit){
                with(binding){
                    cardImage.setImageResource(R.drawable.skipping)
                    cardName.text = community.user.email
                    cardLikes.text = community.likes.toString()
                    cardWeight.text = community.weight.toString() + " Lb"
                    cardDescription.text = community.description
                    cardTitle.text = community.title


                    //cardDescription.text= "Meal "+exercise.numberMeal.toString()
                }

                itemView.setOnClickListener{
                    onClickListener(community)
                }
            }
        }
}