package com.example.reminderapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.AlarmClock
import android.view.View
import android.widget.EditText
import android.widget.TimePicker
import android.icu.util.Calendar
import android.icu.util.Calendar.*
import android.widget.Button
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    lateinit var uTime: EditText
    lateinit var tPicker: TimePicker
    lateinit var button_oneMin : Button
    lateinit var button_twoMin : Button
    lateinit var button_setTimer : Button
    lateinit var button_timePicker : Button
    private val oneMinute: Int = 60
    private val twoMinutes: Int = 60


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tPicker = findViewById(R.id.timePicker)
        uTime = findViewById(R.id.uTime)

        button_oneMin = findViewById(R.id.button_oneMinTimer)
        button_twoMin = findViewById(R.id.button_twoMinTimer)
        button_setTimer = findViewById(R.id.button_userInputTimer)
        button_timePicker = findViewById(R.id.button_TimePicker)

        button_oneMin.setOnClickListener(listener1)
        button_twoMin.setOnClickListener(listener2)
        button_setTimer.setOnClickListener(listener3)
        button_timePicker.setOnClickListener(listener4)
    }

    val listener1 = View.OnClickListener{ view ->
        when (view.getId()){
            R.id.button_oneMinTimer ->{
                clickHandlerOneMin("Alert 1", oneMinute)
            }
        }
    }

    val listener2 = View.OnClickListener{ view ->
        when (view.getId()){
            R.id.button_twoMinTimer ->{
                clickHandlerTwoMin("Alert 2", twoMinutes)
            }
        }
    }

    val listener3 = View.OnClickListener{ view ->
        when (view.getId()){
            R.id.button_userInputTimer ->{
                val min = uTime.text.toString()
                val minutes: Int = min.toInt()
                clickHandlerSetTimer("Alert 3", minutes)
            }
        }
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    val listener4 = View.OnClickListener{ view ->
        when (view.getId()){
            R.id.button_TimePicker ->{
                val hour = tPicker.hour
                val minute = tPicker.minute
                val seconds = getTime(hour, minute)
                clickHandlerTimePicker("Alert 4", seconds)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun clickHandlerTimePicker(message: String, seconds: Int){
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

    }

    fun clickHandlerOneMin(message: String, seconds: Int){
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun clickHandlerTwoMin(message: String, seconds: Int){
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun clickHandlerSetTimer(message: String, minutes: Int){
        val seconds = minutes * 60
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply{
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getTime(hours: Int, minutes: Int) : Int{
        var setTimer: Int = 0
        var timerInSeconds: Int = 0
        var currentTime: Int = 0
        val hourInSeconds = 3600
        val minutesInSeconds = 60
        val dayInSeconds = 86400
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(HOUR_OF_DAY)
        val currentMinute = calendar.get(MINUTE)
        val currentSecond = calendar.get(SECOND)

        currentTime = currentSecond + (currentMinute * minutesInSeconds) + (currentHour * hourInSeconds)
        timerInSeconds = (hours*hourInSeconds) + (minutes * minutesInSeconds)

        if(timerInSeconds < currentTime){
            setTimer = timerInSeconds + (dayInSeconds - currentTime)
        }else {
            setTimer = timerInSeconds - currentTime
        }
        return setTimer
    }


}