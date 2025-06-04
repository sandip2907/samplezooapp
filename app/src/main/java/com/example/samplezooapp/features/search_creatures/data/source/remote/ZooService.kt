package com.example.samplezooapp.features.search_creatures.data.source.remote

import com.example.samplezooapp.features.search_creatures.data.dto.AnimalDto
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface ZooService {

    @GET("/v1/animals")
    suspend fun getAnimals(@Query("name") name: String): Response<List<AnimalDto>>
}