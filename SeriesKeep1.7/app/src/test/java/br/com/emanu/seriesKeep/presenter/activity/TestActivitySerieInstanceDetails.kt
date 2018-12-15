package br.com.edsilfer.moviedb.controller.activity

import android.content.Intent
import android.widget.TextView
import br.com.emanu.seriesKeep.BuildConfig
import br.com.emanu.seriesKeep.R
import br.com.emanu.seriesKeep.activities.SerieDetails_Activity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(22))
class TestActivitySerieInstanceDetails {
    @Test
    fun testActivityMovieDetails() {
        val intent = Intent(Intent.ACTION_VIEW)
        val mActivity = Robolectric.buildActivity(SerieDetails_Activity::class.java).withIntent(intent).create().get()
        val title = mActivity.findViewById(R.id.serie_title) as TextView
    }
}
