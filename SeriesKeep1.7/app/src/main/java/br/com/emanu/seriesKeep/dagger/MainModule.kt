package br.com.emanu.seriesKeep.dagger

import br.com.emanu.seriesKeep.retrofit.RetrofitManager
import br.com.emanu.seriesKeep.retrofit.RestAPIEndPoint
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MainModule() {
    @Provides
    @Singleton
    fun provideRestAPI(): RestAPIEndPoint {
        return RetrofitManager().getInstance().create(RestAPIEndPoint::class.java)
    }
}
