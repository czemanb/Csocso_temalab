package hu.bme.aut.freelancerandroid.util

import android.app.Application
import android.content.Context

class FreelancerApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}