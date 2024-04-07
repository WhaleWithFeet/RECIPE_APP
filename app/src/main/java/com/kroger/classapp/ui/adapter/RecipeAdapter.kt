package com.kroger.classapp.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kroger.classapp.R
import com.kroger.classapp.model.Recipe

class RecipeAdapter(var mlist: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val logo: ImageView = itemView.findViewById(R.id.recipe_image)
        val recipe_tite: TextView = itemView.findViewById(R.id.recipe_title)
    }

    fun setFilteredList(mList: List<Recipe>){
        this.mlist = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.logo.setImageResource(mlist[position].picture)
        holder.recipe_tite.text = mlist[position].title
    }

    override fun getItemCount(): Int {
        return mlist.size
    }
}