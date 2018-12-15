package br.com.emanu.seriesKeep.adapters

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.emanu.kotlin_support.service.log
import br.com.emanu.seriesKeep.R
import br.com.emanu.seriesKeep.AttrConstantes
import br.com.emanu.seriesKeep.SeriesKeep
import br.com.emanu.seriesKeep.Serie_Instance
import br.com.emanu.seriesKeep.activities.SerieDetails_Activity
import com.squareup.picasso.Picasso

class AdapterSerie(
        private val mActivity: AppCompatActivity,
        private val mDataSet: List<Serie_Instance>,
        private val mLayout: Int) : RecyclerView.Adapter<AdapterSerie.SerieHolder>() {

    init {
        SeriesKeep.component?.inject(this)
    }


    class SerieHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cover: ImageView
        val name: TextView
        val loadingWrapper: LinearLayout

        init {
            cover = v.findViewById(R.id.cover) as ImageView
            name = v.findViewById(R.id.name) as TextView
            loadingWrapper = v.findViewById(R.id.loading_wrapper) as LinearLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(mLayout, parent, false)
        return SerieHolder(itemView)
    }

    override fun onBindViewHolder(holder: SerieHolder, position: Int) {
        val serie = mDataSet[position]
        setCover(holder, serie)
        setTitle(holder, serie)
        onCoverClicked(holder, serie)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    private fun setTitle(holder: SerieHolder, serieInstance: Serie_Instance) {
        holder.name.text = serieInstance.originalTitle
    }

    private fun setCover(holder: SerieHolder, serieInstance: Serie_Instance) {
        Picasso.with(mActivity)
                .load(serieInstance.coverUrl)
                .fit()
                .centerCrop()
                .into(holder.cover, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        holder.loadingWrapper.visibility = LinearLayout.GONE
                    }

                    override fun onError() {
                        log("Unable to load serieInstance cover")
                    }
                })
    }

    private fun onCoverClicked(holder: SerieHolder, serieInstance: Serie_Instance) {
        holder.cover.setOnClickListener {
            val intent = Intent(mActivity, SerieDetails_Activity::class.java)
            intent.putExtra(AttrConstantes.ActivityCommunication.ATTR_SERIE, serieInstance)
            mActivity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle())
        }
    }


}
