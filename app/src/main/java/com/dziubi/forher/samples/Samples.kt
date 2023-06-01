package com.dziubi.forher.samples

import com.dziubi.forher.R
import com.dziubi.forher.data.*

val sampleUserOne = UserData(
    id = 1,
    name = "Rachel",
    surname = "Green",
    address = "5th Aveniue 0009 New York",
    email = "green@com.pl",
    password = "Ross"
)

val sampleUserTwo = UserData(
    id = 2,
    name = "Monica",
    surname = "Geller",
    address = "5th Aveniue 0009 New York",
    email = "geller@com.pl",
    password = "Chandler"
)
val sampleProduct = Product(
    id = 1,
    name = "Maska do włosów \n z ekstraktem z granatu",
    ingredients = " Aqua, ",
    desc = " Maska intensywnie nawilzajaco-wygladzająca włosy Botox Ritual preznaczona do włosów niesfornych, puszacych się, suchych oraz zniszczonych",
    price = 25.99f,
    image = R.drawable.natural_cosmetic)

val sampleProductTwo = Product(
    id = 2,
    name = "Szampon do włosów \n z ekstraktem z granatu",
    ingredients = " Aqua, ",
    desc = " Maska intensywnie nawilzajaco-wygladzająca włosy Botox Ritual preznaczona do włosów niesfornych, puszacych się, suchych oraz zniszczonych",
    price = 26.99f,
    image = R.drawable.natural_cosmetic)

val sampleOrderOne = Order(
    id= 1,
    number = "Zamówienie 25487",
    orderState = OrderState.Wysłane,
    date = "25.04.2022, 14:51",
    price = "125 zł"

)

val sampleHeader = listOf("HAIR",  "BODY" , "SKIN" , "PROMOTIONS")

val sampleProducts = buildList {
    for( i in 0..6){
        add(sampleProduct.copy(id=i.toInt()))
    }
}.toList()

val sampleBasketOne = buildList {
    for (i in 0..3) {
        val basket = Basket(product = sampleProduct, i)
        add(basket)
    }
}  .toList()

val sampleOrders = buildList {
    for (i in 0..4){
        add(sampleOrderOne.copy(id = i.toInt()))
    }
}.toList()

val sampleShoppingBag = UiState.ShoppingBag(basket = sampleBasketOne)

val samplePayment = UiState.Payment(basketList = sampleBasketOne)

val sampleProfile = UiState.Profile(name = "Rachel", surname = "Green",email = "green@com.pl" )

val sampleOrderList = UiState.OrderHistory(orderList = sampleOrders)

val sampleEmptyOrderList = UiState.OrderHistory(orderList = emptyList())

val sampleAdress = UiState.Map(
    name = "Rachel",
    surname =" Green",
    sourceAddress = "5th Brooklyn New York City"
)
val cosmeticList = UiState.Main(cosmetic = sampleProducts)

val cosmeticItem = UiState.ProductScreen(product = sampleProduct, alreadyAdded = false)

val sampleProfileUserData = UiState.UserDataScreen(name = "Rachel",
    surname = "Green",
    email = "green@com.pl",
    password = "Ross",
    address = " 5th Aveniue New York")


