package fr.rage.lafie.dto

abstract class BaseShoppingItem(
    open val label: String,
    open val count: Float,
    open val unit: String,
)