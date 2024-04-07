package com.kroger.classapp.data

import com.kroger.classapp.model.RandomRecipe
import com.kroger.classapp.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipeApi {
    @GET("/api/json/v1/1/search.php?s")
    suspend fun getRecipe(): Response<RecipeResponse>

    @GET("/api/json/v1/1/random.php")
    suspend fun getRandomRecipe(): Response<RandomRecipe>
}