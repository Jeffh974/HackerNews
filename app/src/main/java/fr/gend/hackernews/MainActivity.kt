package fr.gend.hackernews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import fr.gend.hackernews.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val app = application as HNApplication

        setSupportActionBar(binding.materialToolbar)
        title = "Coucou"

        thread {
            val ids = app.api.topStories().execute().body()
            runOnUiThread{
                binding.progress.visibility = View.GONE
                if (ids == null) {
                    binding.failed.visibility = View.VISIBLE
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = HNAdapter(ids, app.itemRepository)
                }
            }
        }
    }
}