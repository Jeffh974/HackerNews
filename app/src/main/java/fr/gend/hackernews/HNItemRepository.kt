package fr.gend.hackernews

import android.util.LruCache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HNItemRepository(val api : HackerNewsAPI) {

    val cache = LruCache<Int, Item>(5_000)

    fun fetchItem(id: Int, cb: (Item) -> Unit): () -> Unit {
        val cacheItem = cache[id]

        if (cacheItem != null) {
            cb(cacheItem)
            return {}
        }

        var cancelled = false

        api.item(id).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                val item = response.body()!!
                cache.put(id, item)
                if (!cancelled) cb(item)
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {}
        })
        return { cancelled = true }
    }

}
