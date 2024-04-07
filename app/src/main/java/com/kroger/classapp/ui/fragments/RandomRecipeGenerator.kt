package com.kroger.classapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.kroger.classapp.databinding.FragmentRandomRecipeGeneratorBinding
import com.kroger.classapp.ui.viewmodel.RandomRecipeViewModel
import kotlinx.coroutines.launch

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

    fun setupObservers(){
        lifecycleScope.launch {
            randomRecipeViewModel.randomRecipe.collect { event ->
                when (event){
                    RandomRecipeViewModel.RecipeCharacterEvent.Failure -> {

                        binding.progressBar.isVisible = false
                        binding.errorMessage.isVisible = true
                    }
                    RandomRecipeViewModel.RecipeCharacterEvent.Loading -> {

                        binding.progressBar.isVisible = true
                        binding.errorMessage.isVisible = false
                    }
                    is RandomRecipeViewModel.RecipeCharacterEvent.Success -> {
                        binding.randomName.text = event.randomRecipe.meals.get(0).strMeal
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