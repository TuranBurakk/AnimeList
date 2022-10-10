package com.example.animelist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "model")
data class Model(
    @ColumnInfo(name = "id")
    val id : String? = null,
    @ColumnInfo(name = "title")
    val title : String?= null,
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle : String?= null,
    @ColumnInfo(name = "original_title_romanised")
    @SerializedName("original_title_romanised")
    val originalTitleRomanised : String?= null,
    @ColumnInfo(name = "image")
    val image : String?= null,
    @ColumnInfo(name = "movie_banner")
    @SerializedName("movie_banner")
    val movieBanner : String?= null,
    @ColumnInfo(name = "description")
    val description : String?= null,
    @ColumnInfo(name = "director")
    val director : String?= null,
    @ColumnInfo(name = "producer")
    val producer : String?= null,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate : String?= null,
    @ColumnInfo(name = "running_time")
    @SerializedName("running_time")
    val runningTime : String?= null,
    @ColumnInfo(name = "rt_score")
    @SerializedName("rt_score")
    val rtScore : String?= null,
    @ColumnInfo(name = "url")
    val url : String?= null){

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}
