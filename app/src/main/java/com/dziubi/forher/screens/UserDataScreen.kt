package com.dziubi.forher.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.sampleProfileUserData



@Composable
fun UserDataScreen(
    data: UiState.UserDataScreen,
    onUserAddressChanged: (String) -> Unit = {},
    onUserPasswordChanged: (String) -> Unit ={},
    onBackClick: () -> Unit = {}
    ) {
    var userName by rememberSaveable {
        mutableStateOf(data.name)}

    var userSurname by rememberSaveable {
            mutableStateOf(data.surname)}

    var userEmail by rememberSaveable {
                mutableStateOf(data.email)}

    var userPassword by rememberSaveable {
                    mutableStateOf(data.password)}

    var userAddress by rememberSaveable {
            mutableStateOf(data.address)
    }
    Column(modifier = Modifier.padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally) {

        Box(modifier = Modifier.padding(top = 20.dp, bottom =20.dp),
            contentAlignment = Alignment.CenterStart) {
            Text(text = "Dane użytkownika",
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }
        UserTextField(
            text = "Imię",
            userName = userName,
            onUserNameChanged = { userName = it }
        )
        UserSurnameTextField(
            text = "Nazwisko",
            userSurname = userSurname,
            onUserSurnameChanged = { userSurname = it }
        )
        UserEmailTextField(
            text = "Email",
            userEmail = userEmail,
            onUserEmailChanged = { userEmail = it }
        )
        UserPasswordTextField(
            text = "Hasło",
            userPassword = userPassword,
            onUserPasswordChanged = onUserPasswordChanged)

        UserAddressTextField(
            text = "Adres",
            userAddress = userAddress,
            onUserAddressChanged = onUserAddressChanged)
    }
        Column(modifier = Modifier.padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom){

            OutlinedButton(modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
                onClick = { onBackClick() }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = "WRÓĆ",
                         color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                       )
                }
            }
        }

}
@Composable
fun UserAddressTextField(
    text: String,
    userAddress: String,
    onUserAddressChanged: (String) -> Unit ={},

) {


    Column() {
        Text(modifier = Modifier.padding(start = 16.dp),
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            value = userAddress,
            onValueChange = {
                onUserAddressChanged(it) })
            }
    }

@Composable
fun UserPasswordTextField(
    text: String,
    userPassword: String,
    onUserPasswordChanged: (String) -> Unit,

) {

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)

    }

    Column() {
        Text(modifier = Modifier.padding(start = 16.dp),
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            value = userPassword,
            onValueChange = onUserPasswordChanged,
            visualTransformation =
            if(passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector = image,
                    contentDescription = null)
                }

            })
    }
}
@Composable
fun UserEmailTextField(
    text: String,
    userEmail: String,
    onUserEmailChanged: (String) -> Unit,
    maxChar: Int = 20,
) {


    Column() {
        Text(modifier = Modifier.padding(start =16.dp),
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            value = userEmail,
            onValueChange = {
                if (it.length <= maxChar) {
                    onUserEmailChanged(it)
                }
            },
            singleLine = true)

    }
}
@Composable
fun UserSurnameTextField(
    text: String,
    userSurname: String,
    onUserSurnameChanged: (String) -> Unit,
    maxChar: Int = 20,
) {


    Column() {
        Text(modifier = Modifier.padding(start =16.dp),
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            value = userSurname,
            onValueChange = {
                if (it.length <= maxChar) {
                    onUserSurnameChanged(it)
                }
            },
            singleLine = true)

    }
}

@Composable
fun UserTextField(
    text: String,
    userName: String,
    onUserNameChanged: (String) -> Unit,
    maxChar: Int = 20,
) {


    Column() {
        Text(modifier = Modifier.padding(start =16.dp),
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            value = userName,
            onValueChange = {
                if (it.length <= maxChar) {
                    onUserNameChanged(it)
                }
            },
            singleLine = true)

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserDataScreenPreview() {
    UserDataScreen(sampleProfileUserData)
}