package com.example.samplezooapp.features.search_creatures.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplezooapp.R
import com.example.samplezooapp.core_networking.utils.Result
import com.example.samplezooapp.core_networking.utils.ZooServiceError
import com.example.samplezooapp.features.search_creatures.domain.usecase.GetAnimalsUseCase
import com.example.samplezooapp.features.search_creatures.presentation.util.UiEvent
import com.example.samplezooapp.features.search_creatures.presentation.viewdatamodel.AnimalSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class AnimalSearchViewModel @Inject constructor(private val getAnimalsUseCase: GetAnimalsUseCase): ViewModel() {

    private val _query = MutableStateFlow("")
    private val _searchUiState = MutableStateFlow(AnimalSearchUiState())
    val searchUiState: StateFlow<AnimalSearchUiState> = _searchUiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    init {
        viewModelScope.launch {
            _query
                .debounce(3000)
                .filter { it.length >= 2 }
                .collectLatest { query ->
                    getAnimals(query)
                }
        }
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }
    @VisibleForTesting
     fun getAnimals(query: String){

        viewModelScope.launch {
            _searchUiState.update { state ->
                state.copy(isLoading = true)
            }

            when(val result = getAnimalsUseCase.getAnimals(query = query)){
                is Result.Error -> {
                    _searchUiState.update { state ->
                        state.copy(isLoading = false)
                    }
                    when(result.error){
                        ZooServiceError.SERVER_ERROR -> {
                            _uiEvent.emit(UiEvent.ShowToast(R.string.server_error))
                        }
                        ZooServiceError.REQUEST_TIMEOUT -> {
                            _uiEvent.emit(UiEvent.ShowToast(R.string.rto_error))
                        }
                        ZooServiceError.TOO_MANY_REQUEST -> {
                            _uiEvent.emit(UiEvent.ShowToast(R.string.too_many_request_error))
                        }
                        ZooServiceError.NO_INTERNET -> {
                            _uiEvent.emit(UiEvent.ShowToast(R.string.network_error))
                        }
                        ZooServiceError.MALFORMED_JSON_ERROR -> {
                            _uiEvent.emit(UiEvent.ShowToast(R.string.json_error))
                        }
                        ZooServiceError.UNKNOWN_ERROR -> {
                            _uiEvent.emit(UiEvent.ShowToast(R.string.unknown_error))
                        }
                    }

                }
                is Result.Success -> {
                    _searchUiState.update { state ->
                        state.copy(isLoading = false, animals = result.data)
                    }
                }
            }
        }

    }

}