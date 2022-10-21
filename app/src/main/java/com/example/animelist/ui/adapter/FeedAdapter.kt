package com.example.animelist.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.animelist.R
import com.example.animelist.data.AnimeModel
import com.example.animelist.databinding.ItemRowBinding
import com.example.animelist.ui.fragment.FavItem
import com.example.animelist.ui.fragment.FeedFragmentDirections
import com.example.animelist.utils.downloadFromUrl

class FeedAdapter(private var listener: FavItem? = null) :
    RecyclerView.Adapter<FeedAdapter.AnimeHolder>() {
    private var list = emptyList<AnimeModel>()

    class AnimeHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeHolder {
        val view = LayoutInflater.from(parent.context)
        return AnimeHolder(ItemRowBinding.inflate(view, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeHolder, position: Int) {
        val anime = list[position]
        var resource = checkFavorite(anime.isFavorite)
        changeFavoriteImage(holder.binding.imgFav,resource)
        holder.binding.nameTv.text = anime.title
        holder.binding.scoreTv.text = anime.rtScore
        holder.binding.imageView.downloadFromUrl(anime.image)
        val id = list[position]
        holder.binding.root.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(FeedFragmentDirections.actionFeedFragmentToDetailsFragment(id))
        }
        holder.binding.root.animation = AnimationUtils.loadAnimation(
            holder.binding.root.context,
            R.anim.transition_vertical
        )
        holder.binding.imgFav.setOnClickListener {
            list[position].isFavorite = !anime.isFavorite
            resource = checkFavorite(anime.isFavorite)
            changeFavoriteImage(holder.binding.imgFav, resource)
            listener?.favItem(list[position], position)
        }
    }

    override fun getItemCount() = list.size

    private fun checkFavorite(isFavorite: Boolean): Int {
        return when (isFavorite) {
            true -> R.drawable.ic_baseline_favorite_border
            false -> R.drawable.ic_baseline_favorite_border_24
        }
    }

    private fun changeFavoriteImage(favorite: ImageView, resource: Int) {
        favorite.setImageResource(resource)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAnimeList(newList: List<AnimeModel>) {
        list = newList
        notifyDataSetChanged()
    }
}