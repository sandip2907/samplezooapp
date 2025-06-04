package com.example.samplezooapp.features.search_creatures.data.source

import com.example.composesampleapp.di.IoDispatcher
import com.example.samplezooapp.core_networking.utils.Result

import com.example.samplezooapp.core_networking.utils.ZooServiceError
import com.example.samplezooapp.core_networking.utils.map
import com.example.samplezooapp.core_networking.utils.safeCall
import com.example.samplezooapp.features.search_creatures.data.dto.AnimalDto
import com.example.samplezooapp.features.search_creatures.data.source.remote.ZooService
import com.example.samplezooapp.features.search_creatures.domain.mapper.toDomain
import com.example.samplezooapp.features.search_creatures.domain.model.Animal
import com.example.samplezooapp.features.search_creatures.domain.repository.ZooRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultZooRepository @Inject constructor(private val remoteZooDatSource: ZooService,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
    ): ZooRepository {
    override suspend fun getAnimals(name: String): Result<List<Animal>, ZooServiceError> {
        return withContext(
            coroutineDispatcher
        ){
            safeCall<List<AnimalDto>> {
                remoteZooDatSource.getAnimals(name = name)
            }.map { response ->
                response.map { it.toDomain() }
            }
        }
    }
}