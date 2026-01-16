package com.survicate.uxcam.integration.example

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.survicate.surveys.Survicate

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEventName: EditText
    private lateinit var buttonLog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        editTextEventName = findViewById(R.id.edittext_event_name)
        buttonLog = findViewById(R.id.button_log_event)
        buttonLog.setOnClickListener {
            val eventName = editTextEventName.text.toString()
            Survicate.invokeEvent(eventName)
        }
    }

}
