package com.example.ironathlete.ui.comunity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ironathlete.Community
import com.example.ironathlete.local.exercise.Exercise
import com.example.ironathlete.R
import com.example.ironathlete.databinding.CommunityCardItemBinding
import com.example.ironathlete.databinding.ExerciseCardItemBinding
import com.example.ironathlete.server.ComunityObject
import kotlinx.coroutines.NonDisposableHandle.parent
import java.security.AccessController.getContext

class CommunityAdapter (
    private val communityList: ArrayList<ComunityObject>,
    private val onClickListener:(ComunityObject) -> Unit
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

    fun appendItems(newList: ArrayList<ComunityObject>) {
        communityList.clear()
        communityList.addAll(newList)
        notifyDataSetChanged()
    }

    class CommunityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            // ViewHolder se encarga de pintar, acá dentro vamos a colocar la informacion de nuetro ItemView
            private val binding = CommunityCardItemBinding.bind(itemView)
            fun bind(community: ComunityObject, onClickListener:(ComunityObject) -> Unit){
                with(binding){
                    displayDateTextView.text=community.date
                    displayAgeTextView.text=community.ageUser.toString()
                    displayContentTextView.text=community.content
                    dislayNumberLikesTextView.text=community.numberLikes.toString()
                    displayObjetiveTextView.text=community.objetive
                    numberCommentsTextView.text=community.comments?.size.toString()
                    userNameTextView.text=community.userName

                    likesImageView.setOnClickListener{
                        community.numberLikes = community.numberLikes?.plus(1)
                        dislayNumberLikesTextView.text=community.numberLikes.toString()
                        onClickListener(community)
                    }

                    closeAnswerButton.setOnClickListener {
                        contentCommentsTestView.visibility = View.GONE
                        addNewAnswerTextInputLayout.visibility = View.GONE
                        saveNewCommentButton.visibility = View.GONE
                        closeAnswerButton.visibility = View.GONE
                    }

                    saveNewCommentButton.setOnClickListener {
                        val ans = writeNewAnswerTextEdit.text.toString()
                        if(ans.isEmpty()) Toast.makeText(itemView.context,"Tu respuesta no puede estar vacía",Toast.LENGTH_SHORT).show()
                        else {
                            contentCommentsTestView.text=ans
                            community.comments?.add(ans)
                            numberCommentsTextView.text=community.comments?.size.toString()
                            var total = "RESPUESTAS: \n\n"
                            for(comment in community.comments!!){
                                total+=comment+"\n\n"
                            }
                            contentCommentsTestView.text=total
                            onClickListener(community)
                        }
                    }

                    commentsImageView.setOnClickListener {
                        contentCommentsTestView.visibility = View.VISIBLE
                        addNewAnswerTextInputLayout.visibility = View.VISIBLE
                        saveNewCommentButton.visibility = View.VISIBLE
                        closeAnswerButton.visibility = View.VISIBLE
                        var total = ""
                        for(comment in community.comments!!){
                            total+=comment+"\n\n"
                        }
                        contentCommentsTestView.text=total

                    }
                }
            }
        }
}