package com.dziubi.forher

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dziubi.forher.data.*
import kotlin.math.max

class MainViewModel: ViewModel() {

    private val secretBackend = SecretBackend()

     lateinit var userData: UserData

    var shoppingBag by mutableStateOf(setOf<Basket>())


    var amount by mutableStateOf(0.0)
    private set

    var product by mutableStateOf<UiState.ProductScreen?>(null)
    private set

    var payment by mutableStateOf<UiState.Payment?>(null)
    private  set

    var profileState by mutableStateOf<UiState.Profile?>(null)
    private set

   var profileData by mutableStateOf<UiState.UserDataScreen?>(null)
    private set

    var orderHistory by mutableStateOf(UiState.OrderHistory(listOf(Order(232, OrderState.Wysłane, "Zamówienie nr 1", "08.02.2022", "600"))))
    private set

    var mainScreen by mutableStateOf<UiState.Main?>(null)
    private set

    var selectedOrder by mutableStateOf<Order?>(null)

    suspend fun login(email: String, password: String){
        userData = secretBackend.login(email, password)!!
        updateMainScreen()
    }

    fun updateMainScreen(){
        mainScreen = UiState.Main(cosmetic = secretBackend.getAllProducts()) }

    fun updateProductState(id: Int) {
        val productDetail = secretBackend.getProductDetail(id)
        val isAlreadyAdded = shoppingBag.flatMap { listOf(it.product) }.contains(productDetail)

        val newState = UiState.ProductScreen(
            product = productDetail!!,
            alreadyAdded = isAlreadyAdded
        )
        product = newState
    }
     fun updateProfileState(){
         profileState = UiState.Profile(
             name = userData.name,
             surname = userData.surname,
             email = userData.email
         )
     }
    fun updateProfileData(){
        profileData = UiState.UserDataScreen(
            name = userData.name,
            surname = userData.surname,
            email = userData.email,
            password = userData.password,
            address = userData.address
        )





    }
    fun filterData(str: String){
        if (str.isEmpty() || str.isBlank()){
            mainScreen = mainScreen?.copy(cosmetic = secretBackend.getAllProducts() )
            return
        }
        mainScreen?.cosmetic
            ?.filter { it.name.contains(str, ignoreCase = true) }
            ?.let {filteredList ->
                val actual = mainScreen?.copy(cosmetic = filteredList)
                mainScreen = actual
            }
    }
  fun addItem( itemProduct: Product){
      val basket = Basket(itemProduct, 1)

     shoppingBag = shoppingBag.plusElement(basket)

      val newState = UiState.ProductScreen(
          product = itemProduct,
          alreadyAdded = true
      )
     product = newState

      val totalAmount = shoppingBag.sumOf { it.product.price.toDouble() * it.count }
      val roundedDouble = String.format("%.2f", totalAmount).toDouble()

      amount = roundedDouble
  }
    fun increaseOrderNumber(itemProduct: Product) {
        val basket = shoppingBag.find {it.product == itemProduct}
        val newVal = basket?.count?.plus(1)!!

        basket.count = newVal
        shoppingBag = shoppingBag.plus(basket)

        val totalAmount = shoppingBag.sumOf { it.product.price.toDouble() * it.count }
        val roundedDouble = String.format("%.2f", totalAmount).toDouble()
        amount = roundedDouble
    }
    fun decreaseOrderNumber(itemProduct: Product) {
        val basket = shoppingBag.find {it.product == itemProduct}
        val newValue = max(0, basket?.count?.minus(1)!!)
        basket.count = newValue

        if (newValue == 0){
            shoppingBag = shoppingBag.minus(basket)

            val newState = UiState.ProductScreen(
                product = itemProduct,
                alreadyAdded = false
            )
            product = newState
        }else shoppingBag = shoppingBag.plus(basket)

        val totalAmount = shoppingBag.sumOf { it.product.price.toDouble() * it.count }
        val roundedDouble = String.format("%.2f", totalAmount).toDouble()

        amount = roundedDouble

    }
    fun clearShoppingBag() {
        shoppingBag = setOf()
    }
   fun preparePayment(){
         val newState = UiState.Payment(basketList = shoppingBag.toList())
        payment = newState
    }
}