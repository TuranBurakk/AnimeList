package com.example.animelist.ui.fragment

import com.example.animelist.data.AnimeModel

interface FavItem {
    fun favItem(item: AnimeModel, position: Int)
}