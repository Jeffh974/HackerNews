package fr.gend.hackernews

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HNApplication: Application() {
    val api = Retrofit.Builder()
        .baseUrl("https://hacker-news.firebaseio.com/v0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HackerNewsAPI::class.java)

    val itemRepository = HNItemRepository(api)
}