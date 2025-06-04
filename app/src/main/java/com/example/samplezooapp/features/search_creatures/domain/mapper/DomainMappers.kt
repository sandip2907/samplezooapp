package com.example.samplezooapp.features.search_creatures.domain.mapper

import com.example.samplezooapp.features.search_creatures.data.dto.AnimalDto
import com.example.samplezooapp.features.search_creatures.data.dto.CharacteristicsDto
import com.example.samplezooapp.features.search_creatures.data.dto.TaxonomyDto
import com.example.samplezooapp.features.search_creatures.domain.model.Animal
import com.example.samplezooapp.features.search_creatures.domain.model.Characteristics
import com.example.samplezooapp.features.search_creatures.domain.model.Taxonomy

fun AnimalDto.toDomain(): Animal {
    return Animal(
        name = this.name,
        characteristics = this.characteristics.toDomain(),
        locations = this.locations,
        taxonomy = this.taxonomy.toDomain()
    )
}

fun CharacteristicsDto.toDomain(): Characteristics{
    return Characteristics(
        slogan = this.slogan,
        lifespan = this.lifespan,
        habitat = this.habitat,
        prey = this.prey,
        wingspan = this.wingspan,
        predators = this.predators
    )
}

fun TaxonomyDto.toDomain(): Taxonomy {
    return Taxonomy(
        phylum = this.phylum,
        scientificName = this.scientificName
    )
}