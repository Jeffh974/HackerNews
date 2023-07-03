package fr.gend.hackernews

import android.util.Log
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
        return VH(HnEntryBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val id = ids[position]

        holder.cancel()

        holder.binding.textView.text = "..."

        holder.binding.root.setOnClickListener {
            Log.i("APP", "Click on $id")
        }

        holder.cancel = repo.fetchItem(id) {
            holder.binding.textView.text = it.title
        }
    }

}
