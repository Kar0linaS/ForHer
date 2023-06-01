package com.dziubi.forher.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.dziubi.forher.R
import com.dziubi.forher.data.Order
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.sampleEmptyOrderList
import com.dziubi.forher.samples.sampleOrderList
import com.dziubi.forher.samples.sampleOrders

@Composable
fun OrderHistoryScreen(
    data: UiState.OrderHistory,
    onEmptyOrderHistoryClick: () -> Unit = {},
    onOrderHistoryClick: (Order) -> Unit = {}
) {


    when (data.orderList.isEmpty()) {
        true -> EmptyOrderHistoryList(onEmptyOrderHistoryClick)
        false -> OrderHistory(data.orderList, onOrderHistoryClick = onOrderHistoryClick)
    }
}

@Composable
fun OrderHistory(
    orderList: List<Order>,
    onOrderHistoryClick: (Order) -> Unit
) {
    Column() {
        HeaderHistory()
        Orders(orderList = orderList,
            onOrderHistoryClick = onOrderHistoryClick)
    }
}

@Composable
fun HeaderHistory() {

    Box(modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Historia zamówień",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.Gray

        )
    }

}

@Composable
fun Orders(
    orderList: List<Order>,
    onOrderHistoryClick: (Order) -> Unit
) {
    LazyColumn(){
        items(items = orderList){order ->
            OneOrder(order,
                onOrderHistoryClick = onOrderHistoryClick)
        }
    }
}

@Composable
fun OneOrder(
    order:Order,
    onOrderHistoryClick : (Order) -> Unit
) {
     Surface(modifier = Modifier
         .fillMaxWidth()
         .wrapContentHeight()
         .clickable {onOrderHistoryClick(order)}
         .padding(10.dp),
         shape = RoundedCornerShape(5),
         elevation = 2.dp
     ){
         Column(){
             Text(modifier = Modifier.padding(start =16.dp, top = 10.dp),
                 text = "${order.number}",
                 fontWeight = FontWeight.SemiBold,
                 fontSize = 20.sp,
                 color = Color.Gray
             )
             Row(){
             Text(
                 modifier = Modifier.padding(start= 16.dp),
                 text = "Status: ",
                 fontWeight = FontWeight.SemiBold,
                 color = Color.Gray)
             Text(
                 modifier = Modifier.padding(start= 10.dp),
                 text= "${order.orderState}",
                 fontWeight = FontWeight.SemiBold
             )}

             Text(modifier = Modifier.padding(start = 16.dp),
                 text = "Data:  ${order.date}",
                 fontWeight = FontWeight.Light,
                 color = Color.Gray
             )
             Text(modifier = Modifier.padding(start = 16.dp, bottom = 10.dp),
                 text = "Cena: ${order.price}",
                 fontWeight = FontWeight.SemiBold,
                 color = Color.Gray
             )
         }

     }
}



@Composable
fun EmptyOrderHistoryList(
    onEmptyOrderHistoryClick: () -> Unit = {},
) {
    val image = ImageBitmap.imageResource(id = R.drawable.empty_order_history)


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(modifier = Modifier.size(300.dp, 300.dp),
            bitmap = image,
            contentDescription = null
        )
        Text(modifier = Modifier
            .padding(top = 25.dp),
            text = "Nie masz jeszcze\n żadnych zamówień",
            fontSize = 20.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold
        )
    }
    Column(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedButton(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
            shape = RoundedCornerShape(5),
            onClick = onEmptyOrderHistoryClick
        ) {
            Row() {
                Text(modifier = Modifier
                    .wrapContentHeight(),
                    text = "Zacznij zakupy",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }


        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmptyOrderHistoryListPreview() {
    OrderHistoryScreen(sampleEmptyOrderList)


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderHistoryScreenPreview() {
    OrderHistoryScreen(data = sampleOrderList)
}