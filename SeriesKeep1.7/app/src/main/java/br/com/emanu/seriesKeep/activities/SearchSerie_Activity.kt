package br.com.emanu.seriesKeep.activities

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import br.com.emanu.kotlin_support.service.*
import br.com.emanu.seriesKeep.R
import br.com.emanu.seriesKeep.SeriesKeep
import br.com.emanu.seriesKeep.Serie_Instance
import br.com.emanu.seriesKeep.SearchSeriesResponseWrapper
import br.com.emanu.seriesKeep.adapters.AdapterSerie
import br.com.emanu.seriesKeep.retrofit.RestAPIEndPoint
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_search_serie.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class SearchSerie_Activity : Template_Activity() {

    companion object {
        private val QUERY_PLACEHOLDER = "XYzG$%Y78"
    }

    @Inject
    lateinit var mWebAPI: RestAPIEndPoint

    init {
        SeriesKeep.component?.inject(this)
    }

    override fun startResources() {
        super.startResources()
        loadBackground()
        setQueryListener()
    }



    override fun setupActivity(): ActivitySetup {
        return Factory_Activity.getInstance(this)
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun onNavigationClicked() {
        if ("" == query_container.text.toString()) {
            finish()
        } else {
            query_container.text.clear()
        }
    }

    private fun setQueryListener() {
        query_container.addTextChangedListener(object : QueryWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleNavigationIcon(query_container.text.toString())
                if ("" != query_container.text.toString()) {
                    hideIndeterminateProgressBar()
                    showIndeterminateProgressBar()

                    mWebAPI.searchSeries(query_container.text.toString()).enqueue(object : Callback<SearchSeriesResponseWrapper> {
                        override fun onResponse(call: Call<SearchSeriesResponseWrapper>?, response: retrofit2.Response<SearchSeriesResponseWrapper>?) {
                            hideIndeterminateProgressBar()
                            loadResults(response!!.body()!!.results)
                        }

                        override fun onFailure(call: Call<SearchSeriesResponseWrapper>?, t: Throwable?) {
                            log("error trying to perform tvShow search ${t!!.message!!}")
                            hideIndeterminateProgressBar()
                            showErrorPopUp(R.string.str_error_list_upcoming_events)
                        }
                    })
                } else {
                    loadResults(listOf<Serie_Instance>())
                    result_not_found_wrapper.visibility = CardView.GONE
                }
            }
        })
    }



    private fun loadResults(results: List<Serie_Instance>) {
        doAsync {
            if (results.size > 0) {
                series.visibility = RecyclerView.VISIBLE
                result_not_found_wrapper.visibility = CardView.GONE
                doAsync {
                    val adapter = AdapterSerie(
                            this@SearchSerie_Activity,
                            results,
                            R.layout.rsc_util_serie_small
                    )

                    runOnUiThread {
                        result_not_found_wrapper.visibility = CardView.GONE
                        series.initListItems(
                                this@SearchSerie_Activity as AppCompatActivity,
                                LinearLayoutManager.VERTICAL,
                                null,
                                adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
                    }
                }
            } else {
                series.visibility = RecyclerView.GONE
                result_not_found_wrapper.visibility = CardView.VISIBLE
                result_not_found.text = result_not_found.text.toString().replace(QUERY_PLACEHOLDER, query_container.text.toString())
            }
        }
    }

    private fun loadBackground() {
        Picasso.with(this).load(R.drawable.img_background).fit().centerCrop().into(background)
    }



    private fun toggleNavigationIcon(query: String) {
        if ("" == query) toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp) else toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
    }

    abstract class QueryWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
    }
}
