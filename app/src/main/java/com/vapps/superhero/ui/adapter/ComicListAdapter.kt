package com.vapps.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vapps.superhero.databinding.ComicItemBinding
import com.vapps.superhero.databinding.FragmentHeroDetailBinding

class ComicListAdapter(val comicList: List<String>) : RecyclerView.Adapter<ComicListAdapter.ComicListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListViewHolder {
        return ComicListViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ComicListViewHolder, position: Int) {
        val comic = comicList[position]
        holder.bind(comic)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    class ComicListViewHolder(val binding: ComicItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ComicListViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = ComicItemBinding.inflate(inflater, parent, false)
                return ComicListViewHolder(view)
            }
        }

        fun bind(comic: String) {
            binding.comicVar = comic
        }
    }
}