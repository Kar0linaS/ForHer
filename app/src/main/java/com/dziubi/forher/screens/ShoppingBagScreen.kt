package com.dziubi.forher.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.data.Basket
import com.dziubi.forher.data.Product
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.sampleBasketOne
import com.dziubi.forher.samples.sampleShoppingBag

@Composable
fun ShoppingBagScreen(
    shoppingList: List<Basket>,
    roundedDouble: Double = 0.0,
    onPayClick: () -> Unit = {},
    onDecrementedProductNumber: (Product) -> Unit = {},
    onIncrementedProductNumber: (Product) -> Unit = {}
) {
    Column() {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Koszyk",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
        ShoppingBagList(
            modifier = Modifier.weight(1f),
            shoppingList = shoppingList,
            onDecrementedProductNumber = onDecrementedProductNumber,
            onIncrementedProductNumber = onIncrementedProductNumber)
        SumUp(roundedDouble = roundedDouble,
            onPaymentClick = onPayClick)

    }
}

@Composable
fun SumUp(
    onPaymentClick: () -> Unit = {},
    roundedDouble: Double = 0.0,
) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        SumUpRowText(textSize = 18.sp,
            fontWeight = FontWeight.Light,
            leftText = "Razem",
            rightText = roundedDouble.toString())

        SumUpRowText(textSize = 18.sp,
            fontWeight = FontWeight.Light,
            leftText = "Koszt dostawy",
            rightText = "10")

        SumUpRowText(textSize = 25.sp,
            fontWeight = FontWeight.Bold,
            leftText = "Koszt całkowity",
            rightText = (roundedDouble + 10).toString())

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onPaymentClick)
             {

            Text(modifier = Modifier.padding(10.dp),
                text = "Płatność",
                color = Color.LightGray)
        }
    }

}

@Composable
fun SumUpRowText(
    textSize: TextUnit,
    fontWeight: FontWeight,
    leftText: String,
    rightText: String,
) {

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Text(modifier = Modifier.padding(start = 16.dp),
            text = leftText,
            fontWeight = fontWeight,
            fontSize = textSize
        )

        Text(modifier = Modifier.padding(end = 16.dp),
            text = rightText,
            fontWeight = fontWeight,
            fontSize = textSize,
            textAlign = TextAlign.End
        )
    }
}



@Composable
fun ShoppingBagList(
    modifier: Modifier = Modifier,
    shoppingList: List<Basket>,
    onDecrementedProductNumber: (Product) -> Unit,
    onIncrementedProductNumber: (Product) -> Unit
) {
    LazyColumn(
        modifier = modifier, contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(items = shoppingList) { cosmetic ->
            CosmeticItem(
                cosmetic = cosmetic,
                onDecrementedProductNumber = onDecrementedProductNumber,
                onIncrementedProductNumber = onIncrementedProductNumber
            )
        }
    }
}

@Composable
fun CosmeticItem(
    cosmetic: Basket,
    onDecrementedProductNumber: (Product) -> Unit,
    onIncrementedProductNumber: (Product) -> Unit
) {

    val picture = ImageBitmap.imageResource(id = cosmetic.product.image)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(5)
    ) {
        Row() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(125.dp, 125.dp),
                    bitmap = picture,
                    contentDescription = null
                )
            }
            Column() {
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = cosmetic.product.name
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    val arrowAdd =
                        ImageVector.vectorResource(id = com.dziubi.forher.R.drawable.ic_add)
                    val arrowMinus =
                        ImageBitmap.imageResource(id = com.dziubi.forher.R.drawable.ic_minus)

                    IconButton(modifier = Modifier.border(
                        BorderStroke(
                            1.dp,
                            color = Color.LightGray
                        ),
                        shape = RoundedCornerShape(5)
                    ),
                        onClick = { onDecrementedProductNumber(cosmetic.product) }) {
                        Icon(
                            bitmap = arrowMinus,
                            contentDescription = null
                        )
                    }
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = cosmetic.count.toString()
                    )
                    IconButton(modifier = Modifier.border(
                        BorderStroke(
                            1.dp,
                            color = Color.LightGray
                        ),
                        shape = RoundedCornerShape(5)
                    ),
                        onClick = { onIncrementedProductNumber(cosmetic.product) }) {
                        Icon(
                            modifier = Modifier.size(10.dp, 15.dp),
                            imageVector = arrowAdd,
                            contentDescription = null
                        )
                    }
                    Column() {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = cosmetic.product.price.toString(),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingBagScreenPreview() {
    ShoppingBagScreen(sampleBasketOne)
}