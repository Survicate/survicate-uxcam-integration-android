package com.survicate.uxcam.integration.example

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Exercises the Survicate → UXCam integration path on a real emulator:
 *  1. Launch the sample app.
 *  2. Tap the "log event" button (triggers Survicate.invokeEvent → UXCam.logEvent
 *     via SurvicateUXCamIntegration.onQuestionAnswered).
 *  3. Press home — UXCam auto-stops the session on background
 *     (https://developer.uxcam.com/docs/control-recording).
 *  4. Return to foreground.
 *
 * The assertion is "no crash", which is the realistic bar for a smoke test
 * against a real third-party SDK (we don't mock UXCam's ingestion endpoint).
 */
@RunWith(AndroidJUnit4::class)
class BackgroundFlushSmokeTest {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device = UiDevice.getInstance(instrumentation)
    private val context = instrumentation.targetContext

    @Before
    fun launchAppFromHomeScreen() {
        device.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
            ?.apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }
            ?: error("launch intent missing for $PACKAGE_NAME")
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), TIMEOUT_MS)
    }

    @Test
    fun logEvent_background_foreground_doesNotCrash() {
        val logButton = device.wait(
            Until.findObject(By.res(PACKAGE_NAME, "button_log_event")),
            TIMEOUT_MS
        )
        requireNotNull(logButton) { "button_log_event not found within ${TIMEOUT_MS}ms" }
        logButton.click()

        // Background — per UXCam docs: "UXCam automatically stops a session
        // when the app goes to the background."
        // https://developer.uxcam.com/docs/control-recording
        device.pressHome()
        device.wait(Until.hasObject(By.pkg(device.launcherPackageName).depth(0)), TIMEOUT_MS)

        // Foreground — relaunch via intent.
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
            ?: error("launch intent missing for $PACKAGE_NAME")
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), TIMEOUT_MS)
    }

    private companion object {
        const val PACKAGE_NAME = "com.survicate.uxcam.integration.example"
        const val TIMEOUT_MS = 10_000L
    }
}
