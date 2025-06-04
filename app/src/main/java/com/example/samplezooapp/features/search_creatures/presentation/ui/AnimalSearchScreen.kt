package com.example.samplezooapp.features.search_creatures.presentation.ui

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.samplezooapp.R
import com.example.samplezooapp.features.search_creatures.presentation.util.UiEvent
import com.example.samplezooapp.features.search_creatures.presentation.viewmodel.AnimalSearchViewModel

@Composable
fun AnimalSearchScreen(modifier: Modifier,viewModel: AnimalSearchViewModel = hiltViewModel()){

    var searchText by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

     LaunchedEffect(Unit) {
       viewModel.uiEvent.collect { event ->
           when (event) {
               is UiEvent.ShowToast -> {
                   event.message?.let { Toast.makeText(context, context.getString(it) , Toast.LENGTH_SHORT).show() }
               }
           }
       }
   }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(R.dimen.box_padding_top),
                start = dimensionResource(R.dimen.box_padding_start_end),
                end = dimensionResource(R.dimen.box_padding_start_end)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.onQueryChange(it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search") }
            )

            Spacer(Modifier.height(16.dp))

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = dimensionResource(R.dimen.loading_error_button_padding_top))

                )
            }


            val configuration = LocalConfiguration.current
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.list_padding_top))
                ) {
                    items(uiState.animals) { animal ->
                        AnimalSearchListItem(
                            modifier = Modifier.fillMaxWidth(),
                            animal = animal
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.list_padding_top))
                ) {
                    items(uiState.animals) { animal ->
                        AnimalSearchListItem(
                            modifier = Modifier.fillMaxWidth(),
                            animal = animal
                        )
                    }
                }
            }
        }
    }



}