package com.example.animelist.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.animelist.base.BaseFragment
import com.example.animelist.data.Model
import com.example.animelist.databinding.FragmentFavBinding
import com.example.animelist.ui.adapter.FavAdapter
import com.example.animelist.ui.viewmodel.FavFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : BaseFragment<FragmentFavBinding>(FragmentFavBinding::inflate),FavItem {

    private val viewModel by viewModels<FavFragmentViewModel>()
    private val favAdapter by lazy { FavAdapter() }
    private lateinit var favList : ArrayList<Model>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFav()
        binding.recyclerView2.adapter = favAdapter
    }


   private fun getFav(){
        favList = viewModel.getFav() as ArrayList<Model> /* = java.util.ArrayList<com.example.animelist.data.Model> */
        favAdapter.setData(favList)
   }

    override fun favItem(item: Model, position: Int) {
        viewModel.deleteFromFav(item)
        favList.removeAt(position)
        favAdapter.setData(favList)
        favAdapter.notifyItemRemoved(position)
        favAdapter.notifyDataSetChanged()
    }


}