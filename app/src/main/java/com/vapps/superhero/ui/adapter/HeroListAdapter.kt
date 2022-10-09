package com.vapps.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vapps.superhero.databinding.HeroItemBinding
import com.vapps.superhero.model.Hero
import com.vapps.superhero.model.HeroWithComics

class HeroListAdapter(val clickListener: (heroId: Int) -> Unit) : ListAdapter<HeroWithComics, HeroListAdapter.HeroListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<HeroWithComics>() {
        override fun areItemsTheSame(oldItem: HeroWithComics, newItem: HeroWithComics): Boolean {
            return oldItem.heroEntity.heroId == newItem.heroEntity.heroId
        }

        override fun areContentsTheSame(oldItem: HeroWithComics, newItem: HeroWithComics): Boolean {
            return oldItem.heroEntity.imageLink == newItem.heroEntity.imageLink
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

        fun bind(hero: HeroWithComics, clickListener: (heroId: Int) -> Unit) {
            binding.heroEntity = hero.heroEntity
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                clickListener(hero.heroEntity.heroId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        return HeroListViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        val hero = getItem(position)
        holder.bind(hero, clickListener)
    }
}