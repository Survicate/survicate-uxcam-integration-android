package com.survicate.uxcam.integration.example

import android.app.Application
import com.survicate.surveys.Survicate
import com.survicate.uxcam.integration.SurvicateUXCamIntegration
import com.uxcam.UXCam
import com.uxcam.datamodel.UXConfig

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeUxCam()
        initializeSurvicate()
    }

    private fun initializeUxCam() {
        // provide your key in the local.properties
        val config = UXConfig.Builder(getString(R.string.uxcam_key))
            .enableIntegrationLogging(true)
            .build()
        UXCam.startWithConfiguration(config)
    }

    private fun initializeSurvicate() {
        // provide your key in the local.properties
        Survicate.setWorkspaceKey(getString(R.string.survicate_key))
        Survicate.init(this)
        Survicate.addEventListener(SurvicateUXCamIntegration())
    }

}
