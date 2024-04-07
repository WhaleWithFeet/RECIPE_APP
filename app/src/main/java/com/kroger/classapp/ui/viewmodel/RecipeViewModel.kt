package com.kroger.classapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kroger.classapp.data.model.DataRecipeResponse
import com.kroger.classapp.data.repository.RecipeRepository
import com.kroger.classapp.model.RecipeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel  @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableStateFlow<RecipeCharacterEvent>(RecipeCharacterEvent.Loading)
    val recipes: StateFlow<RecipeCharacterEvent> = _recipes

    fun fillData() = viewModelScope.launch {
        when (val response = recipeRepository.getRecipe()){
            DataRecipeResponse.Error -> _recipes.value = RecipeCharacterEvent.Failure
            is DataRecipeResponse.Success -> _recipes.value = RecipeCharacterEvent.Success(response.recipes)
        }
    }

    sealed class RecipeCharacterEvent {
        data class Success(val recipes: List<RecipeResponse.Meal>) : RecipeCharacterEvent()
        data object Failure : RecipeCharacterEvent()
        data object Loading : RecipeCharacterEvent()
    }
}