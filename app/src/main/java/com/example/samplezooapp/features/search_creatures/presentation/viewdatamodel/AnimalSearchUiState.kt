package com.example.samplezooapp.features.search_creatures.presentation.viewdatamodel

import androidx.annotation.StringRes
import com.example.samplezooapp.features.search_creatures.domain.model.Animal

data class AnimalSearchUiState(
    val isLoading: Boolean = false,
    val animals: List<Animal> = emptyList(),
)
