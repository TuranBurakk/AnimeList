package com.example.animelist.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.animelist.base.BaseFragment
import com.example.animelist.databinding.FragmentDetailsBinding
import com.example.animelist.ui.viewmodel.DetailsFragmentViewModel
import com.example.animelist.utils.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private var id : String = ""
    private val viewModel by viewModels<DetailsFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            id = DetailsFragmentArgs.fromBundle(it).id
        }
            println(id)
        viewModel.getDataFromRoom(id)
        observerLiveData()
    }


    private fun observerLiveData(){
        viewModel.animeLiveData.observe(viewLifecycleOwner) { model ->
            model?.let {
                binding.DetailsTV.text = model.description
                context?.let {
                    binding.imageView2.downloadFromUrl(model.movieBanner)
                }
            }
        }
    }

}