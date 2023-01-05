package com.example.animelist.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.animelist.R
import com.example.animelist.base.BaseFragment
import com.example.animelist.data.AnimeModel
import com.example.animelist.databinding.FragmentDetailsBinding
import com.example.animelist.ui.viewmodel.DetailsFragmentViewModel
import com.example.animelist.utils.Resource
import com.example.animelist.utils.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private val viewModel by viewModels<DetailsFragmentViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        getAnimeDetail()
        setFavoriteButton()
    }
    private fun getAnimeDetail() {
        args.curentItem.id.let {
            viewModel.getDataDetails(it)
                .observe(viewLifecycleOwner) { response ->
                    when (response.status) {
                        Resource.Status.SUCCESS -> {
                            response.data?.let { model -> setData(model) }
                        }
                        Resource.Status.ERROR -> {
                            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }
    }

    private fun setData(data: AnimeModel) = with(binding) {
        this.DetailsTV.text = data.description
        imageView2.downloadFromUrl(data.image)
        tvAnimeName.text = data.title
    }
    private fun initClickListeners() = with(binding){
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        btnFavorite.setOnClickListener {
            if (isInFavorite()) {
                viewModel.deleteFromFavorites(args.curentItem)
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }else{
                viewModel.addToFavorites(args.curentItem)
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }
    }
    private fun isInFavorite(): Boolean {
        var isFavorite = false
        for (favorite in viewModel.getFavorites()){
            if (favorite.id == args.curentItem.id){
                isFavorite = true
            }
        }
        return isFavorite
    }
    private fun setFavoriteButton() = with(binding){
        if (isInFavorite()){
            btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

}