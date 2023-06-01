package com.dziubi.forher.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.R
import com.dziubi.forher.data.Product
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.cosmeticItem


@Composable
fun ProductDetailScreen(
    data: UiState.ProductScreen,
    onItemAdd: (Product) -> Unit = {},
    onGoToShoppingBag: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {


        ProductImage(image = data.product.image)
        ProductDetail(
            product = data.product,
            alreadyAdded = data.alreadyAdded,
            onItemAdd = onItemAdd,
            onGoToShoppingBag = onGoToShoppingBag)

    }
}

@Composable
fun ProductImage(
    image: Int,
) {
    Column {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            val imageBitmap = ImageBitmap.imageResource(id = image)
            Image(
                modifier = Modifier.size(250.dp, 300.dp),
                bitmap = imageBitmap,
                contentDescription = null
            )
        }
    }
}

@Composable
fun ProductDetail(
    product: Product,
    alreadyAdded: Boolean = true,
    onItemAdd: (Product) -> Unit,
    onGoToShoppingBag: () -> Unit,
) {

    var expandedIngredients by rememberSaveable {
        mutableStateOf(true)
    }
    var expandedDesc by rememberSaveable {
        mutableStateOf(true)
    }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = product.price.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                val arrowIcon = when (expandedIngredients) {
                    true -> ImageVector.vectorResource(id = R.drawable.ic_arrow_down)
                    false -> ImageVector.vectorResource(id = R.drawable.ic_arrow_up)
                }
                Text(
                    text = "Składniki",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Image(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable { expandedIngredients = !expandedIngredients },
                    imageVector = arrowIcon,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }
            if (expandedIngredients) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                    text = product.ingredients,
                    fontSize = 15.sp
                )
            }
        }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                val arrowIcon = when (expandedDesc) {
                    true -> ImageVector.vectorResource(id = R.drawable.ic_arrow_down)
                    false -> ImageVector.vectorResource(id = R.drawable.ic_arrow_up)
                }
                Text(
                    text = "Opis użycia",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Image(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable { expandedDesc = !expandedDesc },
                    imageVector = arrowIcon,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }
            if (expandedDesc) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 5.dp),
                    text = product.desc,
                    fontSize = 15.sp
                )
            }
        }

    }
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.Bottom,
    ) {

        var showShoppingDialog by remember {
            mutableStateOf(false)
        }

        ShoppingBagButton(
            alreadyAdded = alreadyAdded,
            onClick = { onItemAdd(product) },
            onGoToShoppingBag = { showShoppingDialog = true })

        if (showShoppingDialog)
            ShoppingDialog(
                onDismiss = { showShoppingDialog = false },
                onExit = {
                    showShoppingDialog = false
                    onGoToShoppingBag()
                }
            )

    }
}

@Composable
fun ShoppingBagButton(
    alreadyAdded: Boolean,
    onClick: () -> Unit = {},
    onGoToShoppingBag: () -> Unit = {},
) {

    val defaultModifier = Modifier
        .padding(vertical = 16.dp)
        .height(48.dp)
        .fillMaxWidth()

    when (alreadyAdded) {
        true -> {

            OutlinedButton(
                modifier = defaultModifier,
                onClick = onGoToShoppingBag,

                ) {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart) {

                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start) {
                        Image(
                            modifier = Modifier.size(24.dp, 24.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
                            contentDescription = null)
                        Text(text = "Dodano", color = Color.Gray, fontSize = 18.sp)
                    }
                }

            }
        }
        false -> {
            OutlinedButton(
                modifier = defaultModifier,
                onClick = onClick)
            { Text(text = stringResource(id = R.string.added_to_cart), color = Color.Gray, fontSize = 18.sp) }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen(cosmeticItem)
}