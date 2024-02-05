package com.survicate.uxcam.integration.testapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.survicate.surveys.Survicate
import com.uxcam.UXCam
import com.uxcam.datamodel.UXConfig

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEventName: EditText
    private lateinit var buttonLog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Survicate initialization is done in [TestApp] class
        initializeUxCam()
        setupViews()
    }

    private fun initializeUxCam() {
        // provide your key in the local.properties
        val config = UXConfig.Builder(getString(R.string.uxcam_key))
            .build()
        UXCam.startWithConfiguration(config)
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
