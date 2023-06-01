package com.dziubi.snoop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.R

@Composable
fun LoginScreen(
    onClickLogin: (String, String) -> Unit,
    onClickGoogle: () -> Unit,
) {
    var email by remember {
        mutableStateOf("green@com.pl")
    }

    var password by remember {
        mutableStateOf("Ross")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        val imageBitmap = ImageBitmap.imageResource(
            id = R.drawable.natural_cosmetic)


        Image(modifier = Modifier
            .sizeIn(minWidth = 60.dp, minHeight = 60.dp),
            bitmap = imageBitmap,
            contentDescription = null)

        Text(modifier = Modifier
            .padding(vertical = 16.dp),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            text = "Zaloguj się do \nswojego konta")


        EmailTextField(
            text = email,
            textLabel = "example@email.com",
            onValueChange = { str -> email = str })

        PasswordTextField(
            text = password,
            textLabel =  "*******" ,
            onValueChange = { str -> password = str})


        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd) {

            Text(modifier = Modifier
                .padding(end = 16.dp, bottom = 16.dp),
                fontWeight = FontWeight.SemiBold,
                text = "Zapomniałeś hasła?",
            color = Color.Magenta) }

        OutlinedButton(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick =  {onClickLogin(email, password) }
            ) {

            Text(text = "Zaloguj się", color = Color.DarkGray)
        }

        OutlinedButton(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(48.dp)
                .fillMaxWidth(),
            onClick = { onClickGoogle() },) {
            Row(verticalAlignment = Alignment.CenterVertically){

                Icon(
                    modifier = Modifier
                        .size(25.dp, 25.dp)
                        .padding(end = 5.dp),
                    bitmap = ImageBitmap.imageResource(id = R.drawable.ic_google),
                    contentDescription = null)
                Text(
                    color = Color.DarkGray,
                    text = "Zaloguj się z Google")
            }
        }
        Box(
            modifier = Modifier
                .padding(top =30.dp),
            contentAlignment = Alignment.BottomCenter){
            Row{
                Text(
                    text = "Nie masz konta?")
                Text(modifier = Modifier.padding(start = 8.dp),
                    text = "Rejestracja",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold)}
        }
    }

}

@Composable
fun PasswordTextField(
    text: String,
    textLabel: String,
    onValueChange: (String) -> Unit,
) {

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    Column {
        Text(modifier = Modifier
            .padding(start = 16.dp),
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            text = "Hasło")

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            value = text,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(percent = 10),
            label = { Text(text = textLabel) },
            leadingIcon = {
                val emailIcon = Icons.Filled.Email
                Icon(imageVector = emailIcon,
                    contentDescription = null)},
            visualTransformation =
            if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)

                }
            }

        )
    }
}

@Composable
fun EmailTextField(
    text: String,
    textLabel: String,
    onValueChange: (String) -> Unit,
) {

    Column {
        Text(modifier = Modifier
            .padding(start = 16.dp),
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            text = "Adres email")

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            value = text,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(percent = 10),
            label = {
                Text(text = textLabel) },
            leadingIcon = {
                val emailIcon = Icons.Filled.Email
                Icon(imageVector = emailIcon,
                    contentDescription = null)
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onClickLogin = { s1, s2 -> }) {
    }
}