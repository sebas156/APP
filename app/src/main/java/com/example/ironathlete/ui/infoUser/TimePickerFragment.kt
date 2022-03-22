package com.example.ironathlete.ui.infoUser

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.ironathlete.R
import java.util.*

class TimePickerFragment(val listener : (String) -> Unit):DialogFragment(), TimePickerDialog.OnTimeSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar :Calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(activity as Context, R.style.TimePicker,this,hour, minute,true)
        return dialog
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        if(minute < 10 && hourOfDay <10) listener("0$hourOfDay:0$minute")
        else if(minute < 10) listener("$hourOfDay:0$minute")
        else if(hourOfDay<10) listener("0$hourOfDay:$minute")
        else listener("$hourOfDay:$minute")
    }

}