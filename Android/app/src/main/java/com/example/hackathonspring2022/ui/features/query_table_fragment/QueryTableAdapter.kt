package com.example.hackathonspring2022.ui.features.query_table_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathonspring2022.R
import com.example.hackathonspring2022.databinding.QueryTableViewHolderBinding
class QueryTableAdapter : RecyclerView.Adapter<QueryTableAdapter.QueryTableViewHolder>() {

    private val items: MutableList<QueryTableData> = mutableListOf()

    fun addData(item: QueryTableData) {
        items.add(item)
        notifyItemInserted(itemCount)
//        items.add(0, item)
//        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryTableViewHolder {
        return QueryTableViewHolder(QueryTableViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: QueryTableViewHolder, position: Int) {
        return holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class QueryTableViewHolder(private val binding: QueryTableViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QueryTableData, position: Int) {
            binding.queryNumber.text = (position + 1).toString()
//            binding.queryNumber.text = (itemCount - position).toString()
            val resource = binding.root.resources

            val textArray = item.queryText?.split("<||>")
            val time = resource.getString(R.string.time) + " " + textArray?.get(0)
            val ip = resource.getString(R.string.ip) + " " + textArray?.get(1)
            val flags = resource.getString(R.string.flags) + " " + textArray?.get(2)

            binding.time.text = time
            binding.ip.text = ip
            binding.flags.text = flags
        }

    }

    data class QueryTableData(
        val queryText: String?
    )

}

