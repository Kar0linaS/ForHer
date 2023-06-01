package com.dziubi.forher.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.data.*
import com.dziubi.forher.samples.sampleBasketOne
import com.dziubi.forher.samples.sampleOrderOne
import com.dziubi.forher.samples.sampleUserOne


@Composable
fun OrderHistoryDetailScreen(
    userData: UserData,
    orderDetail: Order,
    products: List<Basket>
    ) {

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .verticalScroll(scrollState)){
        Box(modifier = Modifier.padding(vertical=10.dp, horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart){
            Text(
                text = "Szczegóły zamówienia",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
        UserDetails(
            name = userData.name,
        surname = userData.surname,
        address = userData.address,
        email = userData.email)
        OrderDetails(
            number = orderDetail.number,
            date = orderDetail.date,
            price = orderDetail.price,
            orderState = orderDetail.orderState)
       ChoosenProducts(products = products )

    }

}

@Composable
fun ChoosenProducts(
    products: List<Basket>
) {
    Column() {
        Text(modifier = Modifier
            .padding(horizontal = 16.dp),
            text = "Produkty",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Gray
        )
    Column() {
        products.forEach { product ->
            ProductsItem(product)
        }
    }
}
}

@Composable
fun ProductsItem(
    product: Basket
) {
    val image = ImageBitmap.imageResource(id = product.product.image)

    Surface(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 16.dp, vertical = 2.dp),
    shape = RoundedCornerShape(5),
    elevation = 2.dp){
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
            Image(modifier = Modifier
                .size(50.dp, 50.dp),
                bitmap = image,
                contentDescription = null
            )
            Text(modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp, end =10.dp),
                text= product.product.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color.Gray
            )
            Column(){
                Text(modifier = Modifier
                    .padding(end = 16.dp),
                    text = "Ilość: ${product.count.toString()}",
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(modifier = Modifier
                    .padding(end = 16.dp),
                    text = "Cena: ${product.product.price.toString()}",
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
    }


@Composable
fun  OrderDetails(
    number: String,
    date: String,
    price: String,
    orderState: OrderState
) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(5),
        elevation = 2.dp) {
        Column() {
            OrderText(
                text = number,
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold)

            OrderText(
                text = date,
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold)

            OrderText(
                text = "Koszt całkowity: $price",
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold)

            OrderText(
                text = "Status: ${orderState.toString()}",
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold)
        }
    }
}
    @Composable
    fun OrderText(
        text :String,
        fontWeight: FontWeight,
        fontSize: TextUnit,
        color: Color
        ) {
            Row(){
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 1.dp, bottom= 2.dp),
                    text = text,
                    fontWeight = fontWeight,
                    fontSize = fontSize,
                    color = color
                )

            }
    }

@Composable
fun UserDetails(
    name: String,
    surname: String,
    address: String,
    email : String
) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(5),
        elevation = 2.dp) {
        Column() {
           UserText(
               text = name,
               fontSize = 20.sp,
               color = Color.Gray,
               fontWeight = FontWeight.SemiBold)

            UserText(
                text = surname,
                fontSize = 20.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold)

            UserText(
                text = address,
                fontSize = 10.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Light)

            UserText(
                text = email,
                fontSize = 10.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold)
        }

    }
}

@Composable
fun UserText(
    text :String,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    color: Color
) {
    Row(){
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 1.dp, bottom= 1.dp),
            text = text,
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color
        )

    }

}

@Preview
@Composable
fun OrderHistoryDetailScreenPreview() {
    OrderHistoryDetailScreen(userData = sampleUserOne,
                             orderDetail = sampleOrderOne,
                             products = sampleBasketOne)
}