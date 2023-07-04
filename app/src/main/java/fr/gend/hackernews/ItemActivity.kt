package fr.gend.hackernews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.gend.hackernews.databinding.ActivityItemBinding
import java.text.SimpleDateFormat
import java.util.Date


class ItemActivity() : AppCompatActivity() {
    lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("itemId", -1)
        if (id == -1) error("Item Id not in intent")

        val app = application as HNApplication

        app.itemRepository.fetchItem(id) {item ->
            val date = SimpleDateFormat("dd/MM/YYYY").format(Date(item.time * 1000))
            val author = "Par : ${item.by} avec un score de ${item.score} (${date})"

            binding.itemTitle.text = item.title
            binding.itemAuthor.text = author

            if (item.url == null) {
                binding.itemUrl.visibility = View.GONE
            } else {
                binding.itemUrl.setOnClickListener{
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                    startActivity(browserIntent)

                }
            }

        }


    }

}