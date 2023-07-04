package fr.gend.hackernews

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.gend.hackernews.databinding.HnEntryBinding

class HNAdapter(val ids: List<Int>, val repo: HNItemRepository) : RecyclerView.Adapter<HNAdapter.VH>() {

    class VH(val binding: HnEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        var cancel : () -> Unit = {}
    }

    override fun getItemCount(): Int = ids.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HnEntryBinding.inflate(layoutInflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val id = ids[position]

        holder.cancel()

        holder.binding.textView.text = "..."

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, ItemActivity::class.java)
            intent.putExtra("itemId", id)
            holder.binding.root.context.startActivity(intent)
        }

        holder.cancel = repo.fetchItem(id) {
            holder.binding.textView.text = it.title
        }
    }
}
