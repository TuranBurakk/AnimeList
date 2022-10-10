package com.example.animelist.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.animelist.base.BaseFragment
import com.example.animelist.data.Model
import com.example.animelist.databinding.FragmentDetailsBinding
import com.example.animelist.ui.viewmodel.DetailsFragmentViewModel
import com.example.animelist.utils.downloadFromUrl


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
        viewModel.animeLiveData.observe(viewLifecycleOwner, Observer { model ->
            model?.let {
                binding.DetailsTV.text = model.description
                context?.let {
                    binding.imageView2.downloadFromUrl(model.movieBanner)
                }
            }
        })
    }

}