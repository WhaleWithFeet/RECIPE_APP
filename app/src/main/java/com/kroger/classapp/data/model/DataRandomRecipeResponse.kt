package com.kroger.classapp.data.model

import com.kroger.classapp.model.RandomRecipe

sealed class DataRandomRecipeResponse {
    data class Success(val recipe: RandomRecipe) : DataRandomRecipeResponse()

    data object Error : DataRandomRecipeResponse()
}