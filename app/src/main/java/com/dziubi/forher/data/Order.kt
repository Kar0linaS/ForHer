package com.dziubi.forher.data

data class Order(
    val id: Int,
    val orderState: OrderState,
    val number: String,
    val date: String,
    val price: String,
    )