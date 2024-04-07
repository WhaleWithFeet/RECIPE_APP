package com.kroger.classapp.data.repository

import com.kroger.classapp.data.RecipeApi
import com.kroger.classapp.data.model.DataRandomRecipeResponse
import com.kroger.classapp.data.model.DataRecipeResponse
import javax.inject.Inject

class RecipeRepositoryReal @Inject constructor(
    private val recipeApi: RecipeApi
) : RecipeRepository {
    override suspend fun getRecipe(): DataRecipeResponse {
        val result = recipeApi.getRecipe()
        return if (result.isSuccessful){
            DataRecipeResponse.Success(result.body()?.meals ?: emptyList())
        }else{
            DataRecipeResponse.Error
        }
    }

    override suspend fun getRandomRecipe(): DataRandomRecipeResponse {
        val result = recipeApi.getRandomRecipe()
        return if (result.isSuccessful){
            DataRandomRecipeResponse.Success(result.body()!!)
        }else{
            DataRandomRecipeResponse.Error
        }
    }

}

