package com.dziubi.forher.screens

import android.graphics.Paint.Align
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.sampleAdress
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    data: UiState.Map
) {
    var isDetailVisible by remember{
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.fillMaxSize()){
        Column{
            Text(modifier = Modifier.padding(start= 16.dp,  top = 16.dp, bottom = 16.dp),
                text = " Twój adres",
            textAlign = TextAlign.Start,
            color = Color.DarkGray,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)

            OrderMap()
        }
        AnimatedVisibility(
            visible = isDetailVisible,
            enter = slideIn { IntOffset(0, 100) } + fadeIn(),
            exit = slideOut { IntOffset(0, 100) } +fadeOut()
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {
                InfoCard(
                    name = data.name,
                    surname = data.surname,
                    address = data.sourceAddress
                )
            }
        }
    }
    Box(contentAlignment = Alignment.BottomCenter){
        OutlinedButton(modifier = Modifier.padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
            onClick = { isDetailVisible = !isDetailVisible }) {
            if (isDetailVisible) Text( text = "Ukryj szczegóły", color = Color.DarkGray, fontSize = 16.sp)
            else Text(text = "Zobacz szczegóły", color = Color.DarkGray, fontSize = 16.sp)
        }
    }

    
}

@Composable
fun InfoCard(
    name: String,
    surname: String,
    address: String
) {
    val imageAddress = ImageBitmap.imageResource(id = com.dziubi.forher.R.drawable.ic_address)

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.25f),
        shape = RoundedCornerShape(topStart =40.dp, topEnd = 40.dp),
        color = Color.LightGray
    ){ Column(modifier = Modifier.padding(top = 30.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ){
        Row(modifier = Modifier.padding(horizontal = 16.dp),
            ){
            Text(
                text = name,
            fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
            fontSize = 25.sp)
            Text(
                text = surname,
                color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp)
        }
          Row(modifier = Modifier.padding(vertical = 10.dp)){
             Image(modifier = Modifier
                 .padding(start = 16.dp)
                 .size(25.dp, 25.dp),
                 bitmap = imageAddress ,
                 contentDescription = null
             )
              Text(modifier = Modifier.padding(start = 10.dp),
                  text = address,
              fontSize = 15.sp,
              color = Color.DarkGray)
          }
    }
        }
}

@Composable
fun OrderMap(modifier: Modifier = Modifier) {

    val startPlace = LatLng(1.25, 103.87)
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(startPlace, 10f)
    }

    Surface(modifier = modifier.fillMaxSize(),
            elevation = 1.dp) {
        GoogleMap(modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState){
            Marker( state = MarkerState(startPlace),
            title = "New York City",
            snippet = "Marker in NYC")
        }

    }

}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(sampleAdress)
}