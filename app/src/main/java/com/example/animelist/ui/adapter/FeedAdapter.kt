package com.example.animelist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.animelist.data.Model
import com.example.animelist.databinding.ItemRowBinding
import com.example.animelist.ui.fragment.FeedFragmentDirections
import com.example.animelist.utils.downloadFromUrl

class FeedAdapter() : RecyclerView.Adapter<FeedAdapter.AnimeHolder>() {
            private var list = emptyList<Model>()
    class AnimeHolder(val binding : ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeHolder {
        val view = LayoutInflater.from(parent.context)
        return AnimeHolder(ItemRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: AnimeHolder, position: Int) {
        holder.binding.nameTv.text = list[position].title
        holder.binding.scoreTv.text = list[position].rtScore
        holder.binding.imageView.downloadFromUrl(list[position].image)
        val id = list[position].id
        holder.binding.root.setOnClickListener {
            Navigation.findNavController(it).navigate(FeedFragmentDirections.actionFeedFragmentToDetailsFragment(id!!))
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
    fun updateAnimeList(newList: List<Model>){
        list = newList
        notifyDataSetChanged()
    }
}