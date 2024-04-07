package com.kroger.classapp.data.repository

import com.kroger.classapp.data.model.DataRandomRecipeResponse
import com.kroger.classapp.data.model.DataRecipeResponse

interface RecipeRepository {
    suspend fun getRecipe(): DataRecipeResponse

    suspend fun getRandomRecipe(): DataRandomRecipeResponse
}