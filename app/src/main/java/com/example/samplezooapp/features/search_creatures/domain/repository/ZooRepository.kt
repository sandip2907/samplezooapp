package com.example.samplezooapp.features.search_creatures.domain.repository

import com.example.samplezooapp.core_networking.utils.ZooServiceError
import com.example.samplezooapp.features.search_creatures.domain.model.Animal
import com.example.samplezooapp.core_networking.utils.Result

interface ZooRepository {

    suspend fun getAnimals(name: String): Result<List<Animal>, ZooServiceError>

}