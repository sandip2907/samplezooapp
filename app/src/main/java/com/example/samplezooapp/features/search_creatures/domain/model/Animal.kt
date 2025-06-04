package com.example.samplezooapp.features.search_creatures.domain.model

data class Animal(
    val name: String?,
    val characteristics: Characteristics,
    val locations: List<String>,
    val taxonomy: Taxonomy)
