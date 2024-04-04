package com.example.testdbapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchResultAdapter(private val results: List<String>) :
    RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    class SearchResultViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return SearchResultViewHolder(textView)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.view.text = results[position]
    }

    override fun getItemCount() = results.size
}
