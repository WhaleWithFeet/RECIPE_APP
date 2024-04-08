package com.kroger.classapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.kroger.classapp.databinding.FragmentRandomRecipeGeneratorBinding
import com.kroger.classapp.ui.viewmodel.RandomRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class RandomRecipeGenerator : Fragment() {
    private var _binding: FragmentRandomRecipeGeneratorBinding? = null
    private val binding get() = _binding!!
    private val randomRecipeViewModel: RandomRecipeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomRecipeGeneratorBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupObservers(){
        lifecycleScope.launch {
            randomRecipeViewModel.randomRecipe.collect { event ->
                when (event){
                    RandomRecipeViewModel.RecipeEvent.Failure -> {

                        binding.progressBar.isVisible = false
                        binding.errorMessage.isVisible = true
                    }
                    RandomRecipeViewModel.RecipeEvent.Loading -> {

                        binding.progressBar.isVisible = true
                        binding.errorMessage.isVisible = false
                    }
                    is RandomRecipeViewModel.RecipeEvent.Success -> {
                        binding.randomName.text = event.randomRecipe.meals[0].strMeal
                        binding.areaOfRecipe.text = event.randomRecipe.meals[0].strArea
                        binding.category.text = event.randomRecipe.meals[0].strCategory
                        binding.subCategories.text = event.randomRecipe.meals[0].strTags
                        binding.instructions.text = event.randomRecipe.meals[0].strInstructions
                        Glide.with(binding.root).load("https://upload.wikimedia.org/wikipedia/commons/9/9e/Fritzi_GSD_puppy.jpg").into(binding.actualImage)
                        binding.nameSection.isVisible = true
                        binding.randomName.isVisible = true
                        binding.progressBar.isVisible = false
                        binding.errorMessage.isVisible = false
                    }
                }
            }
        }
    }



}