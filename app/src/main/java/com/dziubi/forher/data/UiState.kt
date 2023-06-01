package com.dziubi.forher.data


sealed class UiState {
    data class Main(val cosmetic: List<Product>)
    data class Profile(
        val name: String,
        val surname: String,
        val email: String,
    )

    data class ProductScreen(
        val product: Product,
        val alreadyAdded: Boolean = false,
    )

    data class OrderHistory(val orderList: List<Order>)
    data class Payment(val basketList: List<Basket>)
    data class ShoppingBag(val basket: List<Basket>)
    data class UserDataScreen(
        val name: String,
        val surname: String,
        val email: String,
        val password: String,
        val address: String,
    )

    data class Map(
        val name: String,
        val surname: String,
        val sourceAddress: String,
    )

}


