package com.dziubi.forher.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.R
import com.dziubi.forher.data.Product
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.cosmeticList

import com.dziubi.forher.samples.sampleHeader
import com.dziubi.forher.samples.sampleProducts

@Composable
fun MainScreen(
    data: UiState.Main,
    onSearch: (String) -> Unit = {},
    onProductClick: (Product) -> Unit = {},
    onProfileClick: () -> Unit = {}


) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    var selectedCategoryTab by rememberSaveable {
        mutableStateOf("BODY")
    }

    Column {

        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val iconCart = ImageVector.vectorResource(id = com.dziubi.forher.R.drawable.cart_tick)
            val imageProfil =
                ImageBitmap.imageResource(id = com.dziubi.forher.R.drawable.profile_image)

            Text(
                modifier = Modifier.padding(16.dp),
                text = " FOR WOMAN",
                color = Color.Magenta,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )

            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(35.dp, 35.dp),
                imageVector = iconCart,
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .clickable { onProfileClick() }
                    .padding(16.dp)
                    .size(45.dp, 45.dp),
                bitmap = imageProfil,
                contentDescription = null
            )
        }

        SearchField(
            text = text,
            onSearch = {text = it
                onSearch(it)}
        )
        OfferList(
            products = data.cosmetic,
            headers = sampleHeader,
            selectedCategoryTab = selectedCategoryTab,
            onTabClick = { selectedCategoryTab = it},
            onProductClick = onProductClick
        )
    }
}


@Composable
fun SearchField(
    text: String,
    onSearch: (String) -> Unit
) {

    OutlinedTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        value = text,
        onValueChange = { onSearch(it) },
        label = {
            Text(text = " Wyszukaj")
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        })

}

@Composable
fun OfferList(
    headers: List<String>,
    selectedCategoryTab: String,
    onTabClick: (String) -> Unit,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    Column() {
        TabHeader(
            headers = headers,
            selectedTab = selectedCategoryTab,
            onTabClick = onTabClick,
            products = products,
            onProductClick = onProductClick
        )

    }
}

@Composable
fun TabHeader(
    headers: List<String>,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    LazyRow() {
        items(items = headers) { header ->
            Text(modifier = Modifier
                .clickable { onTabClick(header) }
                .padding(horizontal = 10.dp, vertical = 5.dp),
                text = header,
                fontWeight = if (selectedTab == header) FontWeight.Bold else FontWeight.SemiBold,
                fontSize = 25.sp,
                color = if (selectedTab == header) Color.Black else Color.Gray)
        }
    }
    if (selectedTab == "HAIR") {
        HairProducts(products = products, onHairProductClick = onProductClick)
    } else if (selectedTab == "BODY") {
        BodyProducts(products = products, onBodyProductClick = onProductClick)
    } else if (selectedTab == "SKIN") {
        SkinProducts(products = products, onSkinProductClick = onProductClick )
    } else PromotionsAds()
}

@Composable
fun HairProducts(
    products: List<Product>,
    onHairProductClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
            .clickable { onHairProductClick }
    ) {
        items(products) { product ->
            val bitmap = ImageBitmap.imageResource(id = product.image)
            HairProduct(
                bitmap = bitmap,
                hairProduct = product,
                onHairProductClick = onHairProductClick
            )
        }
    }
}

@Composable
fun HairProduct(
    bitmap: ImageBitmap,
    hairProduct: Product,
    onHairProductClick: (Product) -> Unit = {}
) {
    Surface(modifier = Modifier
        .clickable { onHairProductClick(hairProduct) }
        .padding(16.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(2)) {
        Column() {
            Row() {
                Image(
                    modifier = Modifier
                        .size(120.dp, 120.dp),
                    bitmap = bitmap,
                    contentDescription = null,
                    alignment = Alignment.TopCenter
                )
                Surface(
                    shape = RoundedCornerShape(20),
                    elevation = 2.dp
                ) {
                    Box(contentAlignment = Alignment.TopEnd) {
                        Image(modifier = Modifier
                            .size(50.dp, 50.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Gray)

                        )
                    }
                }
            }
            Text(
                text = hairProduct.name
            )
        }
    }
}

@Composable
fun BodyProducts(
    products: List<Product>,
    onBodyProductClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(products) { product ->
            val bitmap = ImageBitmap.imageResource(id = product.image)
            BodyProduct(
                bitmap = bitmap,
                bodyProduct = product,
                onBodyProductClick = onBodyProductClick
            )
        }
    }
}

@Composable
fun BodyProduct(
    bitmap: ImageBitmap,
    bodyProduct: Product,
    onBodyProductClick: (Product) -> Unit = {}
) {
    Surface(modifier = Modifier
        .clickable { onBodyProductClick(bodyProduct) }
        .padding(16.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(2)
    ) {
        Column() {
            Row {
                Image(modifier = Modifier
                    .size(120.dp, 120.dp),
                    bitmap = bitmap,
                    contentDescription = null
                )
                Surface(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20)
                ) {
                    Box(contentAlignment = Alignment.TopEnd) {
                        Image(modifier = Modifier
                            .size(50.dp, 50.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                    }
                }
            }
            Text(text = bodyProduct.name)
        }
    }
}

@Composable
fun SkinProducts(
    products: List<Product>,
    onSkinProductClick : (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(products) { product ->
            val bitmap = ImageBitmap.imageResource(id = product.image)
            SkinProduct(
                productSkin = product,
                bitmap = bitmap,
                onSkinProductClick = onSkinProductClick
            )
        }
    }
}

@Composable
fun SkinProduct(
    productSkin: Product,
    bitmap: ImageBitmap,
    onSkinProductClick: (Product) -> Unit = {}
) {
    Surface(modifier = Modifier
        .clickable { onSkinProductClick(productSkin) }
        .padding(16.dp),
        shape = RoundedCornerShape(2)) {
        Column() {
            Row() {
                Image(modifier = Modifier
                    .size(125.dp, 125.dp),
                    bitmap = bitmap,
                    contentDescription = null
                )
                Surface(
                    shape = RoundedCornerShape(2),
                    elevation = 2.dp
                ) {
                    Box(contentAlignment = Alignment.TopEnd) {
                        Image(modifier = Modifier
                            .size(50.dp,50.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                    }
                }
            }
            Text(text = productSkin.name)
        }

    }
}

@Composable
fun PromotionsAds() {

    Surface() {
        Box(contentAlignment = Alignment.CenterStart){
            Text(
                text = "Aktualnie brak promocji"
            )
        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(cosmeticList)
}