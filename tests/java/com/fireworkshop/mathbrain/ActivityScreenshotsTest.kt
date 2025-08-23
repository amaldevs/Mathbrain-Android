package com.fireworkshop.mathbrain

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.Ignore

@Ignore("Requires instrumentation environment")
class ActivityScreenshotsTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun mainActivity() = paparazzi.snapshotActivity<MainActivity>()

    @Test
    fun gameActivity() = paparazzi.snapshotActivity<GameActivity>()

    @Test
    fun highscoresActivity() = paparazzi.snapshotActivity<HighscoresActivity>()

    @Test
    fun scoresActivity() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), ScoresActivity::class.java).apply {
            putExtra(GameActivity.TotalTime, "0")
            putExtra(GameActivity.Wrong, "0")
        }
        paparazzi.snapshotActivity<ScoresActivity>(intent)
    }

    @Test
    fun helpActivity() = paparazzi.snapshotActivity<HelpActivity>()

    @Test
    fun creditsActivity() = paparazzi.snapshotActivity<CreditsActivity>()

    @Test
    fun splashActivity() = paparazzi.snapshotActivity<SplashActivity>()
}

private inline fun <reified T : Activity> Paparazzi.snapshotActivity(intent: Intent = Intent(ApplicationProvider.getApplicationContext(), T::class.java)) {
    ActivityScenario.launch<T>(intent).use { scenario ->
        scenario.onActivity { activity ->
            snapshot(activity.window.decorView)
        }
    }
}
