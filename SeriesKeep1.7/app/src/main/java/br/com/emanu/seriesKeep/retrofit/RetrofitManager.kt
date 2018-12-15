package br.com.emanu.seriesKeep.retrofit

import br.com.emanu.kotlin_support.service.log
import br.com.emanu.seriesKeep.R
import br.com.emanu.seriesKeep.SeriesKeep
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitManager() {
    internal var gson: Gson
    internal val client : OkHttpClient
    internal var retrofit: Retrofit

    init {
        client =  OkHttpClient.Builder().addInterceptor { chain ->
            var request = chain.request()
            val url = request
                    .url()
                    .newBuilder()
                    .addQueryParameter(
                            SeriesKeep.getContext().resources.getString(R.string.str_server_routes_attr_api_key),
                            SeriesKeep.getContext().resources.getString(R.string.str_server_routes_base_api_v3)
                    ).build()

            log("Performing HTTP request to: $url")

            request = request.newBuilder().url(url).build();
            chain.proceed(request)
        }.build()

        gson = GsonBuilder()
                .setDateFormat(R.string.date_format)
                .create()

        retrofit = Retrofit.Builder()
                .baseUrl(SeriesKeep.getContext().resources.getString(R.string.str_comm_base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
    }

    fun getInstance(): Retrofit {
        return retrofit
    }
}
