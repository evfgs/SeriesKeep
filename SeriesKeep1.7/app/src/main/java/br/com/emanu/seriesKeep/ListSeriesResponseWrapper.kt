package br.com.emanu.seriesKeep

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class ListSeriesResponseWrapper(
        //Retorno da API para homepage
        @SerializedName("page")
        @Expose
        var page: Long? = null,
        @SerializedName("results")
        @Expose
        var results: List<Serie_Instance> = ArrayList(),
        @SerializedName("total_results")
        @Expose
        var totalResults: Long? = null,
        @SerializedName("total_pages")
        @Expose
        var totalPages: Long? = null
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
