package br.com.emanu.seriesKeep.activities

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.emanu.kotlin_support.service.hideCircularProgressBar
import br.com.emanu.kotlin_support.service.initListItems
import br.com.emanu.kotlin_support.service.showCircularProgressBar
import br.com.emanu.kotlin_support.service.showErrorPopUp
import br.com.emanu.seriesKeep.R
import br.com.emanu.seriesKeep.SeriesKeep
import br.com.emanu.seriesKeep.ListSeriesResponseWrapper
import br.com.emanu.seriesKeep.Serie_Instance
import br.com.emanu.seriesKeep.DrawerController
import br.com.emanu.seriesKeep.adapters.AdapterSerie
import br.com.emanu.seriesKeep.retrofit.RestAPIEndPoint
import kotlinx.android.synthetic.main.act_homepage.*
import kotlinx.android.synthetic.main.rsc_homepage_content.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class Homepage_Activity : Template_Activity() {

    @Inject
    lateinit var mRestAPI: RestAPIEndPoint

    init {
        SeriesKeep.component?.inject(this)
    }

    override fun setupActivity(): ActivitySetup {
        return Factory_Activity.getInstance(this)
    }

    override fun startResources() {
        super.startResources()
        setLeftSlideAnimationForCaller()
        loadBackgroundImage(background)
        showCircularProgressBar()
        DrawerController(this).init()

        mRestAPI.getSeries().enqueue(object : Callback<ListSeriesResponseWrapper> {
            override fun onResponse(call: Call<ListSeriesResponseWrapper>?, response: retrofit2.Response<ListSeriesResponseWrapper>?) {
                hideCircularProgressBar()
                loadSeries(response!!.body()!!.results)
            }

            override fun onFailure(call: Call<ListSeriesResponseWrapper>?, t: Throwable?) {
                hideCircularProgressBar()
                showErrorPopUp(R.string.str_error_list_upcoming_events)
            }
        })
    }

    private fun loadSeries(results: List<Serie_Instance>) {
        doAsync {
            val adapter = AdapterSerie(
                    this@Homepage_Activity,
                    results,
                    R.layout.rsc_util_serie_large
            )

            runOnUiThread {
                series.initListItems(
                        this@Homepage_Activity,
                        LinearLayoutManager.VERTICAL,
                        null,
                        adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
                )
            }
        }
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search -> startActivity(Intent(this, SearchSerie_Activity::class.java))
        }
        return true
    }


}

