package com.vapps.superhero.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable


data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int,
)