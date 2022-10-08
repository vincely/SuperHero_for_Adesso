package com.vapps.superhero.data.dto

data class Thumbnail(
    val extension: String,
    val path: String
) {
    fun getLink(): String {
        return "$path.$extension"
    }
}