package com.example.samplezooapp

import android.app.Application
import com.example.samplezooapp.config.EnvironmentManager
import com.example.samplezooapp.config.EnvironmentType
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SampleZooApplication : Application(){

    @Inject
    lateinit var appEnvManager: EnvironmentManager

    override fun onCreate() {
        super.onCreate()
        setupEnvironment()
    }

    private fun setupEnvironment() {

        val currentEnvironment = when {
            BuildConfig.FLAVOR == "dev" -> EnvironmentType.DEV
            BuildConfig.FLAVOR == "prod" -> EnvironmentType.PROD
            BuildConfig.FLAVOR == "staging" -> EnvironmentType.STAGE
            else -> {
                EnvironmentType.DEV
            }
        }
        appEnvManager(
            environmentType = currentEnvironment,
            applicationName = "EmployeeDirectoryApp",
            appVersion = BuildConfig.VERSION_NAME
        )
    }
}