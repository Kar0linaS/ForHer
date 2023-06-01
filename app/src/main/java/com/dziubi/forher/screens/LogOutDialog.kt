package com.dziubi.forher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun LogOutDialog(
    onDismiss: () -> Unit = {},
    onExit: () -> Unit = {},

) {
   Dialog(onDismissRequest = { onDismiss() },
   properties = DialogProperties(
        dismissOnBackPress = false,
       dismissOnClickOutside = false
   )) {
       Card(
           shape = RoundedCornerShape(20),
           modifier = Modifier
               .fillMaxWidth()
               .padding(8.dp),
           elevation = 8.dp
       ){
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .background(Color.White)
           ){
               Row( modifier = Modifier
                   .fillMaxWidth()
                   .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)){
                   Text( text = "Czy chcesz się wylogować? ",
                   fontSize =  15.sp, fontWeight = FontWeight.SemiBold )
               }
           }
           Row(modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)){
               OutlinedButton(onClick = {onDismiss() },
                   Modifier
                       .fillMaxWidth()
                       .padding(8.dp)
                       .weight(1F)
               ) {
                   Text (text = "NIE")
               }
               Button( onClick = { onExit()},
                   Modifier
                   .fillMaxWidth()
                   .padding(8.dp)
                   .weight(1F)
               ) {
                   Text( text = "TAK")
               }
           }



       }
   }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogOutDialogPreview(){
    LogOutDialog()
}