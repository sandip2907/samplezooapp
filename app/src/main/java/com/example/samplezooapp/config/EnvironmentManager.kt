package com.example.samplezooapp.config

import javax.inject.Inject


class EnvironmentManager @Inject constructor() {

    val API_KEY = "X-Api-Key"
    val CACHE_CONTROL = "Cache-Control"
    private lateinit var environmentType: EnvironmentType
    private lateinit var applicationName: String
    private lateinit var appVersion: String

    operator fun invoke(
        environmentType: EnvironmentType,
        applicationName: String,
        appVersion: String
    ){
       this.environmentType = environmentType
       this.appVersion = appVersion
       this.applicationName = applicationName
    }
    fun getEnvironmentType(): EnvironmentType{
        return environmentType
    }
}

enum class EnvironmentType{
    DEV,
    STAGE,
    PROD
}

