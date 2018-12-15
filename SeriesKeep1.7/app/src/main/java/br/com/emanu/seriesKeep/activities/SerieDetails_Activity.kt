package br.com.emanu.seriesKeep.activities

import android.support.v7.widget.Toolbar
import br.com.emanu.seriesKeep.R
import br.com.emanu.seriesKeep.AttrConstantes
import br.com.emanu.seriesKeep.SeriesKeep
import br.com.emanu.seriesKeep.Serie_Instance
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_serie_details.*
import kotlinx.android.synthetic.main.rsc_serie_header.*
import org.jetbrains.anko.doAsync

class SerieDetails_Activity : Template_Activity() {

    private var mSerieInstance: Serie_Instance? = null

    init {
        SeriesKeep.component?.inject(this)
    }

    override fun setupActivity(): ActivitySetup {
        return Factory_Activity.getInstance(this)
    }

    override fun startResources() {
        super.startResources()
        setLeftSlideAnimationForCalled()
        window.setBackgroundDrawable(getDrawable(R.drawable.img_background))
        doAsync {
            mSerieInstance = intent.extras.getSerializable(AttrConstantes.ActivityCommunication.ATTR_SERIE) as Serie_Instance
            if (null == mSerieInstance) throw IllegalArgumentException("${SerieDetails_Activity::class.simpleName} requires a Serie_Instance object to start")
            runOnUiThread {
                loadSerieDetails()
                loadOverview()
            }
        }
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun onNavigationClicked() {
        onBackPressed()
    }

    private fun loadSerieDetails() {
        loadCover()
        serie_title.text = mSerieInstance!!.title
        rating_bar.rating = (mSerieInstance!!.voteAverage * 5 / 10).toFloat()
    }

    private fun loadCover() {
        Picasso.with(this).load(mSerieInstance!!.coverUrl).fit().into(cover)
    }

    private fun loadOverview() {
        serie_overview.text = if (null != mSerieInstance) mSerieInstance!!.overview else getString(R.string.str_act_serie_dtls_txt_overview_not_found)
    }
}

