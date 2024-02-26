package com.survicate.uxcam.integration.example

import android.app.Application
import com.survicate.surveys.Survicate
import com.survicate.uxcam.integration.SurvicateUXCamIntegration

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeSurvicate()
    }

    private fun initializeSurvicate() {
        // provide your key in the local.properties
        Survicate.setWorkspaceKey(getString(R.string.survicate_key))
        Survicate.init(this)
        Survicate.addEventListener(SurvicateUXCamIntegration())
    }
}
