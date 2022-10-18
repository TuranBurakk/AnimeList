package com.example.animelist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "model")
data class AnimeModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo val id : String,
    @ColumnInfo val title : String?= null,
    @SerializedName("original_title")
    @ColumnInfo val originalTitle : String?= null,
    @SerializedName("original_title_romanised")
    @ColumnInfo val originalTitleRomanised : String?= null,
    @ColumnInfo val image : String?= null,
    @SerializedName("movie_banner")
    @ColumnInfo val movieBanner : String?= null,
    @ColumnInfo val description : String?= null,
    @ColumnInfo val director : String?= null,
    @ColumnInfo val producer : String?= null,
    @SerializedName("release_date")
    @ColumnInfo val releaseDate : String?= null,
    @SerializedName("running_time")
    @ColumnInfo val runningTime : String?= null,
    @SerializedName("rt_score")
    @ColumnInfo val rtScore : String?= null,
    @ColumnInfo val url : String?= null)
   {

}
