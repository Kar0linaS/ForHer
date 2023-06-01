package com.dziubi.forher.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.R

@Composable
fun StartScreen(
    onStartClick: () -> Unit = {}
) {

 val imageStart = ImageBitmap.imageResource(id = R.drawable.start)

    Image(modifier = Modifier
        .fillMaxSize(),
        bitmap = imageStart,
        alpha = 0.5f,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(modifier = Modifier.padding(bottom = 10.dp),
            text = "FOR HER",
            color = Color.Blue,
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold
        )
        Text(modifier = Modifier.padding(top = 10.dp, bottom = 40.dp),
            text =" ekologiczne \n kosmetyki",
            color = Color.DarkGray,
            fontSize = 35.sp,
            fontWeight = FontWeight.SemiBold
        )
        OutlinedButton(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
            .fillMaxWidth()
            .height(48.dp),
            onClick = onStartClick
        ) {
            val iconArrow = ImageVector.vectorResource(id = R.drawable.ic_arrow_right)

              Row(
                  verticalAlignment = Alignment.CenterVertically,

              ){
                  Text(
                      text = "Zaloguj się",
                      color = Color.DarkGray,
                      fontSize = 15.sp
                  )
                  Icon(modifier = Modifier
                      .padding(start = 10.dp),
                      imageVector = iconArrow,
                      contentDescription = null,
                      tint = Color.DarkGray)
              }
        }


    }


}
@Preview(showSystemUi = true, showBackground = true)
@Composable
 fun StartScreenPreview() {
    StartScreen()

}