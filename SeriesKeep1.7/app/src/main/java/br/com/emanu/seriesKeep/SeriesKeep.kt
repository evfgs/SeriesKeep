package br.com.emanu.seriesKeep

import android.app.Application
import android.content.Context
import br.com.emanu.seriesKeep.dagger.DaggerMainComponent
import br.com.emanu.seriesKeep.dagger.MainComponent
import br.com.emanu.seriesKeep.dagger.MainModule

class SeriesKeep : Application() {
    companion object {
        private var mApp: SeriesKeep? = null
        private var mComponent: MainComponent? = null

        val component: MainComponent?
            get() {
                if (null == mComponent) {
                    return DaggerMainComponent.builder().mainModule(MainModule()).build()
                } else {
                    return mComponent
                }
            }

        fun getContext(): Context {
            if (mApp == null) {
                return Application()
            }
            return mApp!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        mComponent =  DaggerMainComponent.builder().mainModule(MainModule()).build()
    }
}
