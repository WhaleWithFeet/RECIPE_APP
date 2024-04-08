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

    private val _randomRecipe = MutableStateFlow<RecipeEvent>(RecipeEvent.Loading)
    val randomRecipe: StateFlow<RecipeEvent> = _randomRecipe

    fun getRandom() = viewModelScope.launch {
        when (val response = recipeRepository.getRandomRecipe()){
            DataRandomRecipeResponse.Error -> _randomRecipe.value = RecipeEvent.Failure
            is DataRandomRecipeResponse.Success -> _randomRecipe.value = RecipeEvent.Success(response.recipe)
        }
    }

    sealed class RecipeEvent {
        data class Success(val randomRecipe: RandomRecipe) : RecipeEvent()
        data object Failure : RecipeEvent()
        data object Loading : RecipeEvent()
    }




}