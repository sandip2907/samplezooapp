package com.example.samplezooapp.features.search_creatures.data.source.remote

import com.example.samplezooapp.config.EnvironmentType


object ServiceConfig {
    const val ZOO_SERVICE_RETROFIT_INSTANCE = "zoo_service_retrofit_instance"

    fun getBaseUrl(environmentType: EnvironmentType): String{

        return when(environmentType){

            EnvironmentType.DEV -> {"https://api.api-ninjas.com"}
            EnvironmentType.PROD -> {"https://api.api-ninjas.com"}
            EnvironmentType.STAGE -> {"https://api.api-ninjas.com"}
        }
    }
}