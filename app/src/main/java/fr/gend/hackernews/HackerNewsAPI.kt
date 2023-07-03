package fr.gend.hackernews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class Item (
    val by: String,
    val kids: List<Int>,
    val score: Int,
    val time: Long,
    val title: String,
    val url: String
    )

interface HackerNewsAPI {
    @GET("topstories.json")
    fun topStories(): Call<List<Int>>

    @GET("item/{id}.json")
    fun item(@Path("id") id: Int): Call<Item>
}

