package com.example.animelist.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.animelist.base.BaseFragment
import com.example.animelist.data.Model
import com.example.animelist.databinding.FragmentFeedBinding
import com.example.animelist.ui.adapter.FeedAdapter
import com.example.animelist.ui.viewmodel.FeedFragmentViewModel
import com.example.animelist.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) , FavItem {

    private val viewModel : FeedFragmentViewModel by viewModels()
    private val adapter by lazy { FeedAdapter() }
    private val animelist = arrayListOf<Model>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataFromAPI()
        binding.animeRecyclerView.adapter = adapter
        getAnimes()
        adapter.updateAnimeList(animelist)
    }


    private fun getAnimes(){
        viewModel.getDataFromAPI().observe(viewLifecycleOwner){response ->
            when(response.status){
                Resource.Status.SUCCESS ->{
                    animelist.addAll((response.data ?: arrayListOf(animelist)) as Collection<Model>)
                }
                Resource.Status.ERROR ->{
                    showDialog(requireContext(), message = "${response.message}")  }
            }
        }
    }
    fun showDialog(
        context: Context,
        title: String? = "Uyarı",
        message: String?,
        cancellable: Boolean = true,
        action: () -> Unit = {}
    ) {
        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setCancelable(cancellable)
            setPositiveButton("Tamam") { _, _ -> action.invoke() }
        }.show()
    }

    override fun favItem(item: Model, position: Int) {

    }


}