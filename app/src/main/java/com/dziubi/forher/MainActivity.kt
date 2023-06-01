package com.dziubi.forher

import PaymentScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.dziubi.forher.screens.*
import com.dziubi.forher.ui.theme.ForHerTheme
import com.dziubi.snoop.ui.screens.LoginScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForHerTheme {
                val navController = rememberNavController()
                val vm = viewModel<MainViewModel>()

                NavigationComponent(navController = navController, vm = vm)
            }
        }
    }
}
@Composable
fun NavigationComponent(
    navController: NavHostController,
    vm: MainViewModel,
) {
    val loginScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = "start") {

        composable(route = "start") {
            StartScreen(onStartClick = { navController.navigate(route = "login") })
        }
        composable(route = "login") {
            LoginScreen(onClickLogin = { email, password ->
                loginScope.launch {
                    vm.login(email, password)
                    navController.navigate(route = "main")
                }
            },
                onClickGoogle = {})
        }
        composable(route = "main") {
            val mainScreen = vm.mainScreen

            if (mainScreen != null)
                MainScreen(data = mainScreen,
                    onProductClick = { product ->
                        vm.updateProductState(id = product.id)
                        navController.navigate("product detail")
                    },
                    onProfileClick = {
                        vm.updateProfileState()
                        navController.navigate("profile")
                    },
                    onSearch = { str -> vm.filterData(str) })
        }
        composable(route = "profile") {
            val profileData = vm.profileState

            if (profileData != null) {
                ProfileScreen(
                    data = profileData,
                    onHistoryClick = {
                        navController.navigate("order_history")
                    },
                    onClick = {
                        navController.navigate("main")
                    },
                    onAddressClick ={
                                    navController.navigate("map")
                    },
                    onProfileClick = {
                        vm.updateProfileData()
                        navController.navigate("user_data_screen")
                    },
                    onLogOutClick = {
                        navController.navigate("start")
                    }
                )
            }
        }
        composable(route = "product detail") {
            val productDetail = vm.product

            if (productDetail != null)
                ProductDetailScreen(
                    data = productDetail,
                    onItemAdd = { product -> vm.addItem(product) },
                    onGoToShoppingBag = { navController.navigate("shopping_bag") })
        }
        composable("shopping_bag") {

            val orderList = vm.shoppingBag.toList()
            val amount = vm.amount

            ShoppingBagScreen(
                shoppingList = orderList,
                roundedDouble = amount,
                onDecrementedProductNumber = { vm.decreaseOrderNumber(it) },
                onIncrementedProductNumber = { vm.increaseOrderNumber(it) },
                onPayClick = {
                    vm.preparePayment()
                    navController.navigate("payment")
                })
        }
        composable("payment") {
            val payment = vm.payment

            if (payment != null) {
                PaymentScreen(
                    data = payment,
                    onPaymentClick = {
                        vm.clearShoppingBag()
                        navController.navigate("main")
                    })
            }
        }
        composable("order_history") {
            val orderHistory = vm.orderHistory

            OrderHistoryScreen(
                data = orderHistory,
                onEmptyOrderHistoryClick = {
                    navController.navigate(route = "home", navOptions = navOptions {
                        popUpTo("order_history") {
                            inclusive = true
                        }
                    })
                },
                onOrderHistoryClick = {
                    vm.selectedOrder = it
                    navController.navigate("order_detail_screen")
                }
            )
        }
        composable("order_detail_screen"){


            OrderHistoryDetailScreen(
                userData = vm.userData,
                orderDetail = vm.selectedOrder!!,
                products = vm.shoppingBag.toList())
        }



     composable("user_data_screen"){
         val userProfileData = vm.profileData

        if(userProfileData != null)
       UserDataScreen(
           data = userProfileData,
           onUserAddressChanged = {},
         onUserPasswordChanged = {},
         onBackClick = {
             navController.navigate("profile")
         })
     }
      composable("map"){

      }
    }
}









