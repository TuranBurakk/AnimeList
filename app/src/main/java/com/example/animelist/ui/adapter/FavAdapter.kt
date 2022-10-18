package com.example.animelist.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.animelist.R
import com.example.animelist.data.AnimeModel
import com.example.animelist.databinding.ItemRowBinding
import com.example.animelist.ui.fragment.FavFragmentDirections
import com.example.animelist.ui.fragment.FavItem
import com.example.animelist.utils.downloadFromUrl

class FavAdapter(private var listener: FavItem? = null): RecyclerView.Adapter<FavAdapter.FavHolder>() {
    private var favList = emptyList<AnimeModel>()
    class FavHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHolder {
        val view = LayoutInflater.from(parent.context)
        return FavHolder(ItemRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: FavHolder, position: Int) {
        val fav = favList[position].id
        holder.binding.nameTv.text = favList[position].title
        holder.binding.scoreTv.text = favList[position].rtScore
        holder.binding.imageView.downloadFromUrl(favList[position].image)
        holder.binding.imgFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        holder.binding.root.setOnClickListener {
            it.findNavController().navigate(FavFragmentDirections.actionFavFragmentToDetailsFragment(fav))
        }
        holder.binding.root.animation = AnimationUtils.loadAnimation(holder.binding.root.context,
        R.anim.transition_vertical)
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList : List<AnimeModel>){
        favList = newList
        notifyDataSetChanged()
    }
    fun unFav(position: Int){
        listener?.let {
            listener?.favItem(favList[position], position)
        }
    }
}