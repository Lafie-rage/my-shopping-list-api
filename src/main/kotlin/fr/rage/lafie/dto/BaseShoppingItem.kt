package fr.rage.lafie.dto

import kotlinx.serialization.Serializable

@Serializable
abstract class BaseShoppingItem {
    abstract val label: String
    abstract val count: Float
    abstract val unit: String
}