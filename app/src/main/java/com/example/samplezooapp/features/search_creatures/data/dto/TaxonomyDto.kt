package com.example.samplezooapp.features.search_creatures.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaxonomyDto(
    val kingdom: String?,
    val phylum: String?,
    @Json(name = "class") val specificClass: String?,
    val order: String?,
    val family: String?,
    val genus: String?,
    @Json(name = "scientific_name") val scientificName: String?
)
