package com.survicate.uxcam.integration.testapp

import android.app.Application
import com.survicate.surveys.Survicate
import com.survicate.uxcam.integration.SurvicateUXCamIntegration

class TestApp : Application() {

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
