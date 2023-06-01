package com.dziubi.forher

import com.dziubi.forher.data.Order
import com.dziubi.forher.data.Product
import com.dziubi.forher.data.UserData
import com.dziubi.forher.samples.sampleProduct
import com.dziubi.forher.samples.sampleProductTwo
import com.dziubi.forher.samples.sampleUserOne
import com.dziubi.forher.samples.sampleUserTwo
import kotlinx.coroutines.delay

class SecretBackend {

    private val users = listOf(sampleUserOne, sampleUserTwo)
    private val products = listOf(sampleProduct, sampleProductTwo)
    private val history = arrayListOf<Order>()

    suspend fun login(email: String, password: String) : UserData?{
        delay(2000)
        return users.find {it.email == email && it.password == password}
    }
    fun getProductDetail(id: Int): Product?{
        return products.find { it.id == id }
    }

    fun getAllProducts(): List<Product>{
        return products
    }
    fun getUsers(name: String, surname: String): UserData?{
        return users.find { it.name == name && it.surname == surname }
    }

     fun saveOrderHistory( orders: Set<Order>){
         history.addAll(orders)
     }
    fun getOrderHistory() =  history.toList()
    }

