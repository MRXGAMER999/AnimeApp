package com.example.animeapp.domain.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animeapp.util.Constants.HERO_REMOTE_KEYS_DATABASE_TABLE

@Immutable
@Entity(tableName = HERO_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
