package com.survicate.uxcam.integration.example

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.survicate.surveys.Survicate

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEventName: EditText
    private lateinit var buttonLog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        applyWindowInsets()
    }

    private fun setupViews() {
        editTextEventName = findViewById(R.id.edittext_event_name)
        buttonLog = findViewById(R.id.button_log_event)
        buttonLog.setOnClickListener {
            val eventName = editTextEventName.text.toString()
            Survicate.invokeEvent(eventName)
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.content_layout)) { view, windowInsets ->
            val insets = windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() or
                        WindowInsetsCompat.Type.displayCutout() or
                        WindowInsetsCompat.Type.ime()
            )
            view.updatePadding(
                left = insets.left,
                top = insets.top,
                right = insets.right,
                bottom = insets.bottom,
            )
            windowInsets
        }
    }

}
