package com.example.samplezooapp.features.search_creatures.presentation.viewmodel

import com.example.samplezooapp.features.search_creatures.domain.model.Animal
import com.example.samplezooapp.features.search_creatures.domain.model.Characteristics
import com.example.samplezooapp.features.search_creatures.domain.model.Taxonomy
import com.example.samplezooapp.features.search_creatures.domain.usecase.GetAnimalsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

import com.example.samplezooapp.core_networking.utils.Result

class AnimalSearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Mock
    private lateinit var getAnimalsUseCase: GetAnimalsUseCase

    private lateinit var viewModel: AnimalSearchViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = AnimalSearchViewModel(getAnimalsUseCase)
    }

    @Test
    fun `Given when getAnimals gets call Then return successful response`() = testScope.runTest {
        // Given
        val animals = listOf(
            Animal(
            name = "Dog", Characteristics(
                slogan = "The Loyal animal",
                lifespan = "15",
                habitat = "Domestic",
                prey = "Smaller animals",
                predators = "none",
                wingspan = ""
            ),
            locations = listOf("Africa, Asia, Australia, Europe, North America, South America"),
            taxonomy = Taxonomy(phylum = "chordata", scientificName = "panthera leo")
        ),Animal(
            name = "Cat", Characteristics(
                slogan = "I like running",
                lifespan = "20",
                habitat = "Residential",
                prey = "all animals",
                predators = "All bigger animals",
                wingspan = ""
            ),
            locations = listOf("Africa, Asia, Australia, Europe, North America, South America"),
            taxonomy = Taxonomy(phylum = "chordata", scientificName = "panthera leo")
        ))
        val name = "cats"
        `when`(getAnimalsUseCase.getAnimals(name)).thenReturn(Result.Success(animals))

        // When
        viewModel.getAnimals(name)

        // Then
        assertEquals(animals, viewModel.searchUiState.value.animals)
        assertEquals(false, viewModel.searchUiState.value.isLoading)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}