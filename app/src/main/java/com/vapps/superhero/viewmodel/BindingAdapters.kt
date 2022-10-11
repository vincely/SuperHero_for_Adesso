package com.vapps.superhero.viewmodel

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vapps.superhero.R
import com.vapps.superhero.model.ComicWithHeroes
import com.vapps.superhero.model.Hero
import com.vapps.superhero.model.HeroWithComics
import com.vapps.superhero.ui.adapter.ComicListAdapter
import com.vapps.superhero.ui.adapter.HeroListAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.ic_launcher_foreground)
            error(com.google.android.material.R.drawable.mtrl_ic_error)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<HeroWithComics>?) {
    val adapter = recyclerView.adapter as HeroListAdapter
    adapter.submitList(data)
}

/*@BindingAdapter("listComicData")
fun bindComicRecyclerView(recyclerView: RecyclerView, data: List<ComicWithHeroes>?) {
    val adapter = recyclerView.adapter as ComicListAdapter
    adapter.submitList(data)
}*/

@BindingAdapter("customBgColor")
fun setBackGround(view: View, color: String?) {
    color?.let {
        view.setBackgroundColor(it.toInt())
    }
}