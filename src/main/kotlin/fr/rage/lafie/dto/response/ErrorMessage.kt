package fr.rage.lafie.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorMessage(
    val code: String,
    val reason: String,
    val stackTrace: List<String>,
)