package com.example.samplezooapp.features.search_creatures.domain.usecase

import com.example.samplezooapp.core_networking.utils.ZooServiceError
import com.example.samplezooapp.core_networking.utils.Result
import com.example.samplezooapp.features.search_creatures.domain.model.Animal
import com.example.samplezooapp.features.search_creatures.domain.repository.ZooRepository
import javax.inject.Inject

class GetAnimalsUseCase @Inject constructor(private val zooRepository: ZooRepository) {

    suspend fun getAnimals(query: String): Result<List<Animal>, ZooServiceError>{
        return zooRepository.getAnimals(name = query)
    }
}