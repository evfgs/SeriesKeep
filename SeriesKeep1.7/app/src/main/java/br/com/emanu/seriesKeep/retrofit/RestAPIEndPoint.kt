package br.com.emanu.seriesKeep.retrofit

import br.com.emanu.seriesKeep.ListSeriesResponseWrapper
import br.com.emanu.seriesKeep.SearchSeriesResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RestAPIEndPoint {

    @GET("tv/popular/")
    fun getSeries(): Call<ListSeriesResponseWrapper>

    @GET("search/tv/")
    fun searchSeries(@Query("query") name: String): Call<SearchSeriesResponseWrapper>
}
