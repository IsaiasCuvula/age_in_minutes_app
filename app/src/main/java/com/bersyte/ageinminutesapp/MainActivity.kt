package com.bersyte.ageinminutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bersyte.ageinminutesapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectDate.setOnClickListener {view->
            clickDatePicker(view)
        }

    }

    private fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this, {
                    view, year, month, dayOfMonth ->

            val selectedDate = "$dayOfMonth/${month + 1}/$year"

            binding.tvSelectedDate.text = selectedDate

            val sdf =  SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinute = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinute = currentDate!!.time / 60000

            val differenceInMinutes = currentDateToMinute - selectedDateInMinute
            binding.tvSelectedDateInMinute.text = differenceInMinutes.toString()

            }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }
}