package com.mgarma.jadkuls2

import android.app.Application
import com.mgarma.jadkuls2.data.AppContainer
import com.mgarma.jadkuls2.data.DefaultAppContainer

class JadwalKuliahApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
