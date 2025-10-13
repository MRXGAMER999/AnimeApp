package com.example.animeapp.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
@Immutable
@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<Hero> = emptyList(),
    val lastUpdated: Long? = null
)
