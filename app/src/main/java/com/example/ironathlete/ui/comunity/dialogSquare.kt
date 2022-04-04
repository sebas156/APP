package com.example.ironathlete.ui.comunity

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.ironathlete.R
import com.squareup.picasso.Picasso

public class dialogSquare(contexto: Context, sendData: FinishedDialogSquare) {

    public interface FinishedDialogSquare{
        fun ResultDialogSquare(result: String)
    }

    private var interfaz: FinishedDialogSquare = sendData

    init {
        val dialog =Dialog(contexto)
        dialog.setContentView(R.layout.dialog_add_forum)
        val content: EditText = dialog.findViewById(R.id.input_add_forum_edit_text)
        val button_accept: Button = dialog.findViewById(R.id.acept_comment_button)
        val button_denay: Button = dialog.findViewById(R.id.denay_dialog_function_button)
        val image_social: ImageView=dialog.findViewById(R.id.social_image_view)
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/ironathlete.appspot.com/o/others%2Fsocial_real.jpg?alt=media&token=83505056-6fe8-4249-b7e5-b2e0fc8f7cc2").into(image_social)
        button_accept.setOnClickListener{
            sendData.ResultDialogSquare(content.text.toString())
            dialog.dismiss()
        }
        button_denay.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}