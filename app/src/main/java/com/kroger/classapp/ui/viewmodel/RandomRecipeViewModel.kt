package com.kroger.classapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kroger.classapp.data.model.DataRandomRecipeResponse
import com.kroger.classapp.data.repository.RecipeRepository
import com.kroger.classapp.model.RandomRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomRecipeViewModel  @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _randomRecipe = MutableStateFlow<RecipeCharacterEvent>(RecipeCharacterEvent.Loading)
    val randomRecipe: StateFlow<RecipeCharacterEvent> = _randomRecipe

    fun getRandom() = viewModelScope.launch {
        when (val response = recipeRepository.getRandomRecipe()){
            DataRandomRecipeResponse.Error -> _randomRecipe.value = RecipeCharacterEvent.Failure
            is DataRandomRecipeResponse.Success -> _randomRecipe.value = RecipeCharacterEvent.Success(response.recipe)
        }
    }

    sealed class RecipeCharacterEvent {
        data class Success(val randomRecipe: RandomRecipe) : RecipeCharacterEvent()
        data object Failure : RecipeCharacterEvent()
        data object Loading : RecipeCharacterEvent()
    }




}