package com.example.samplezooapp.features.search_creatures.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacteristicsDto(
    val prey: String?,
    @Json(name = "name_of_young") val nameOfYoung: String? = null,
    @Json(name ="group_behavior") val groupBehavior: String? = null,
    @Json(name ="estimated_population_size") val estimatedPopulationSize: String? = null,
    @Json(name ="biggest_threat") val biggestThreat: String? = null,
    @Json(name ="most_distinctive_feature") val mostDistinctiveFeature: String? = null,
    @Json(name ="other_name(s)") val otherName: String?,
    @Json(name ="gestation_period") val gestationPeriod: String?,
    val habitat: String?,
    val predators: String?,
    val diet: String?,
    @Json(name ="average_litter_size") val averageLitterSize: String? = null,
    val lifestyle: String? = null,
    @Json(name ="common_name") val commonName: String? = null,
    @Json(name ="number_of_species") val numberOfSpecies: String? = null,
    val location: String? = null,
    val slogan: String?,
    val group: String?,
    val color: String?,
    @Json(name ="skin_type") val skinType: String?,
    @Json(name ="top_speed") val topSpeed: String?,
    val lifespan: String?,
    val weight: String? = null,
    val length: String? = null,
    @Json(name ="age_of_sexual_maturity") val ageOfSexualMaturity: String? = null,
    @Json(name ="age_of_weaning") val ageOfWeaning: String? = null,
    val wingspan: String?,
)
