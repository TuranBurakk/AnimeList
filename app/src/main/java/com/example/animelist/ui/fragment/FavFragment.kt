package com.example.animelist.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.animelist.base.BaseFragment
import com.example.animelist.data.AnimeData
import com.example.animelist.data.AnimeModel
import com.example.animelist.databinding.FragmentFavBinding
import com.example.animelist.ui.adapter.FavAdapter
import com.example.animelist.ui.viewmodel.FavFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : BaseFragment<FragmentFavBinding>(FragmentFavBinding::inflate),FavItem {

    private val viewModel by viewModels<FavFragmentViewModel>()
    private val favAdapter by lazy { FavAdapter(this) }

    private lateinit var favList : ArrayList<AnimeModel>

   private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
       override fun onMove(
           recyclerView: RecyclerView,
           viewHolder: RecyclerView.ViewHolder,
           target: RecyclerView.ViewHolder
       ): Boolean {
           return  true
       }

       override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
           val layoutPosition = viewHolder.layoutPosition
           val selectedAnime = favAdapter.favList[layoutPosition]
           viewModel.deleteFromFav(selectedAnime)
       }
   }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFav()
        binding.FavRv.adapter = favAdapter
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.FavRv)
    }


   private fun getFav(){
        favList = viewModel.getFav() as ArrayList<AnimeModel> /* = java.util.ArrayList<com.example.animelist.data.Model> */
        favAdapter.setData(favList)
   }

    @SuppressLint("NotifyDataSetChanged")
    override fun favItem(item: AnimeModel, position: Int) {
        viewModel.deleteFromFav(item)
        favList.removeAt(position)
        favAdapter.setData(favList)
        favAdapter.notifyItemRemoved(position)
        favAdapter.notifyDataSetChanged()
    }


}