package com.vapps.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vapps.superhero.databinding.HeroItemBinding
import com.vapps.superhero.model.Hero

class HeroListAdapter : ListAdapter<Hero, HeroListAdapter.HeroListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Hero>() {
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.imageLink == newItem.imageLink
        }
    }

    class HeroListViewHolder(private var binding: HeroItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup) : HeroListViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = HeroItemBinding.inflate(inflater, parent, false)
                return HeroListViewHolder(view)
            }
        }

        fun bind(hero: Hero) {
            binding.hero = hero
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        return HeroListViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        val hero = getItem(position)
        holder.bind(hero)
    }
}