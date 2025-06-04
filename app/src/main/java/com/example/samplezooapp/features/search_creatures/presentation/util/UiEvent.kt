package com.example.samplezooapp.features.search_creatures.presentation.util

import androidx.annotation.StringRes

sealed class UiEvent {
    data class ShowToast(@StringRes val message: Int?) : UiEvent()
}