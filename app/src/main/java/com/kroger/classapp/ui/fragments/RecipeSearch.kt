package com.kroger.classapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kroger.classapp.R
import com.kroger.classapp.databinding.FragmentRecipeSearchBinding
import com.kroger.classapp.model.Recipe
import com.kroger.classapp.model.RecipeResponse
import com.kroger.classapp.ui.adapter.RecipeAdapter
import com.kroger.classapp.ui.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

class RecipeSearch : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<Recipe>()
    private lateinit var adapter: RecipeAdapter

    private var _binding: FragmentRecipeSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeSearchBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        searchView = binding.recipeSearchBar
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        addDataToList()
        adapter = RecipeAdapter(mList)

        recyclerView.adapter = adapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return inflater.inflate(R.layout.fragment_recipe_search, container, false)
    }
    private fun filterList(query :  String?){
        if(query != null){
            val filterList = ArrayList<Recipe>()
            for(i in mList){
                if (i.title.toLowerCase(Locale.ROOT).contains(query)){
                    filterList.add(i)
                }
            }
            if(filterList.isEmpty()){
                Toast.makeText(requireContext().applicationContext, "No Data found", Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filterList)
            }
        }
    }

    private fun addDataToList(){
        mList.add(Recipe("Eggs", R.drawable.dredd))
        mList.add(Recipe("Bacon", R.drawable.dredd_2))
        mList.add(Recipe("Pickle", R.drawable.dredd_3))
        mList.add(Recipe("Cheese", R.drawable.dredd))
        mList.add(Recipe("Lettuce", R.drawable.dredd_2))
        mList.add(Recipe("Salad", R.drawable.dredd_3))
        mList.add(Recipe("Carrot", R.drawable.dredd))
        mList.add(Recipe("Lamb Chops", R.drawable.dredd_2))
        mList.add(Recipe("Salami", R.drawable.dredd_3))
    }

    /*@SuppressLint()
    fun refreshData(recipes: List<RecipeResponse>){
        mList.clear()
        //mList.addAll(recipes)
    }
    /*fun setupObservers(){
        lifecycleScope.launch {
            RecipeViewModel.recipes.collect { event ->
                when (event){
                    RecipeViewModel.RecipeCharacterEvent.Failure -> {

                    }
                    RecipeViewModel.RecipeCharacterEvent.Loading -> {

                    }
                    is RecipeViewModel.RecipeCharacterEvent.Success -> {

                    }
                }
            }
        }
    }

     */

     */

}
