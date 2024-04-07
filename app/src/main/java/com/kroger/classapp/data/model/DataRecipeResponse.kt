package com.kroger.classapp.data.model

import com.kroger.classapp.model.RecipeResponse

sealed class DataRecipeResponse {
    data class Success(val recipes: List<RecipeResponse.Meal>) : DataRecipeResponse()

    data object Error : DataRecipeResponse()
}