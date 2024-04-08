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
import kotlinx.coroutines.launch
import java.util.Locale


class RecipeSearch : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: RecipeAdapter
    private var mList = ArrayList<Recipe>()
    private val recipeViewModel: RecipeViewModel by activityViewModels()


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

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        mList.add(Recipe("Eggs", R.drawable.screenshot__139_))
        mList.add(Recipe("Bacon", R.drawable.screenshot__139_))
        mList.add(Recipe("Pickle", R.drawable.screenshot__139_))
        mList.add(Recipe("Cheese", R.drawable.screenshot__139_))
        mList.add(Recipe("Lettuce", R.drawable.screenshot__139_))
        mList.add(Recipe("Salad", R.drawable.screenshot__139_))
        mList.add(Recipe("Carrot", R.drawable.screenshot__139_))
        mList.add(Recipe("Lamb Chops", R.drawable.screenshot__139_))
        mList.add(Recipe("Salami", R.drawable.screenshot__139_))
    }

    @SuppressLint()
    fun refreshData(recipes: List<RecipeResponse>){
        mList.clear()
        //mList.addAll(recipes)
    }
    fun setupObservers(){
        lifecycleScope.launch {
            recipeViewModel.recipes.collect { event ->
                when (event){
                    RecipeViewModel.RecipeEvent.Failure -> {

                    }
                    RecipeViewModel.RecipeEvent.Loading -> {

                    }
                    is RecipeViewModel.RecipeEvent.Success -> {

                    }
                }
            }
        }
    }



}
