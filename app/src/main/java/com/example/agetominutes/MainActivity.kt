package com.example.agetominutes

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null // initialize with null
    private var tvShowAgeInMinutes :TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btndate)
        // defined here tvSelectedDate
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        // define here tvdisplaydate
        tvShowAgeInMinutes=findViewById(R.id.tvdisplaydate)
        btnDatePicker.setOnClickListener {
            Toast.makeText(this, "button pressed", Toast.LENGTH_SHORT).show()
            clickDatePicker()
        }
    }

    fun clickDatePicker() {
        // To access the calendar, we need a Calendar instance
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog
        val dpd  = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                // Do something with the selected date
                Toast.makeText(
                    this,
                    "Date selected is $selectedDayOfMonth/${selectedMonth+1}/$selectedYear",
                    Toast.LENGTH_LONG
                ).show()
                val selectedDate ="$selectedDayOfMonth / ${selectedMonth+1} / $selectedYear"
                tvSelectedDate?.setText(selectedDate)
                val sdf =SimpleDateFormat("d / M / yyyy", Locale.ENGLISH)
                val date =sdf.parse(selectedDate)
                var SelectedDateInMinutes = date.time/60000
                var CurrentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                var CurrentDateInMinutes = CurrentDate.time/ 60000
                var differennce = CurrentDateInMinutes-SelectedDateInMinutes
                tvShowAgeInMinutes?.text= differennce.toString()


            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate= System.currentTimeMillis()-86400
        dpd.show()


    }
}