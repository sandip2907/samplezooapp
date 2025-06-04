package com.example.samplezooapp.features.search_creatures.data.dto

data class AnimalDto(
    val name: String,
    val taxonomy: TaxonomyDto ,
    val locations: List<String>,
    val characteristics: CharacteristicsDto
)
