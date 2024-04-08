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

    private val _recipes = MutableStateFlow<RecipeEvent>(RecipeEvent.Loading)
    val recipes: StateFlow<RecipeEvent> = _recipes

    fun fillData() = viewModelScope.launch {
        when (val response = recipeRepository.getRecipe()){
            DataRecipeResponse.Error -> _recipes.value = RecipeEvent.Failure
            is DataRecipeResponse.Success -> _recipes.value = RecipeEvent.Success(response.recipes)
        }
    }

    sealed class RecipeEvent {
        data class Success(val recipes: List<RecipeResponse.Meal>) : RecipeEvent()
        data object Failure : RecipeEvent()
        data object Loading : RecipeEvent()
    }
}