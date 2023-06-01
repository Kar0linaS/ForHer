package com.dziubi.forher.screens


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dziubi.forher.R
import com.dziubi.forher.data.UiState
import com.dziubi.forher.samples.sampleProfile


@Composable
fun ProfileScreen(
    data: UiState.Profile,
    onHistoryClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},
    onPaymentClick: () -> Unit = {},
    onLogOutClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onClick: () -> Unit = {},

    ) {

    Column() {
        ProfileHeader(onClick = onClick)
        ProfileInfo(
            name = data.name,
            surname = data.surname,
            email = data.email)
        ProfileMenu(
            onHistoryClick = onHistoryClick,
            onProfileClick = onProfileClick,
            onAddressClick = onAddressClick,
            onPaymentClick = onPaymentClick)
        ProfileHelp(
            onLogOutClick = onLogOutClick,
            onHelpClick = onHelpClick)
    }
}

@Composable
fun ProfileHelp(
    onLogOutClick: () -> Unit,
    onHelpClick: () -> Unit,
) {

    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Bottom) {

        HelpButton(
            resourceId = R.drawable.ic_history,
            text = "POMOC",
            onClick = onHelpClick
        )

        var showLogoutDialog by remember { mutableStateOf(false) }
        HelpButton(
            resourceId = null,
            text = "WYLOGUJ",
            onClick = { showLogoutDialog = true }
        )

        if (showLogoutDialog)
            LogOutDialog(
                onDismiss = { showLogoutDialog = false },
                onExit = {
                    showLogoutDialog = false
                    onLogOutClick()
                }
            )
    }
}

@Composable
fun HelpButton(
    @DrawableRes resourceId: Int?,
    text: String,
    onClick: () -> Unit,
) {

    val arrowIcon = ImageVector.vectorResource(id = R.drawable.ic_arrow_right)
    val displayIcon = if (resourceId != null)
        ImageBitmap.imageResource(id = resourceId)
    else null

    OutlinedButton(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (displayIcon != null)
                Image(
                    modifier = Modifier
                        .size(25.dp, 25.dp),
                    bitmap = displayIcon,
                    contentDescription = null)
            Text(modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f),
                text = text,
                color = Color.DarkGray
            )
            Image(imageVector = arrowIcon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray))

        }


    }
}


@Composable
fun ProfileMenu(
    onHistoryClick: () -> Unit,
    onProfileClick: () -> Unit,
    onAddressClick: () -> Unit,
    onPaymentClick: () -> Unit,
) {

    Column(modifier = Modifier.padding(top = 10.dp)
    ) {
        ProfileButton(
            resourceId = R.drawable.ic_history,
            buttonText = "Historia",
            onClick = onHistoryClick)
        ProfileButton(
            resourceId = null,
            buttonText = "Profil",
            onClick = onProfileClick
        )
        ProfileButton(
            resourceId = R.drawable.ic_address,
            buttonText = "Adres",
            onClick = onAddressClick
        )
        ProfileButton(
            resourceId = R.drawable.ic_payments,
            buttonText = "Płatność",
            onClick = onPaymentClick
        )
    }
}

@Composable
fun ProfileButton(
    @DrawableRes resourceId: Int?,
    buttonText: String,
    onClick: () -> Unit,

    ) {

    val arrowIcon = ImageVector.vectorResource(id = R.drawable.ic_arrow_right)
    val displayIcon = if (resourceId != null)
        ImageBitmap.imageResource(id = resourceId)
    else null

    OutlinedButton(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 3.dp),
        onClick = onClick,
        shape = RoundedCornerShape(5)) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically)
        {
            if (displayIcon != null)
                Image(modifier = Modifier
                    .padding(start = 10.dp)
                    .size(25.dp, 25.dp),
                    bitmap = displayIcon,
                    contentDescription = null)
            Text(modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp),
                text = buttonText,
                textAlign = TextAlign.Start,
                color = Color.DarkGray)
            Image(modifier = Modifier.padding(end = 16.dp),
                imageVector = arrowIcon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray))
        }


    }

}

@Composable
fun ProfileInfo(
    name: String,
    surname: String,
    email: String,
) {

    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Row() {
            Text(modifier = Modifier.padding(start = 16.dp, end = 6.dp),
                text = name,
                fontSize = 20.sp
            )
            Text(text = surname,
                fontSize = 20.sp)
        }
        Text(modifier = Modifier.padding(horizontal = 16.dp),
            text = email)
    }

}

@Composable
fun ProfileHeader(
    onClick: () -> Unit,
) {

    val arrowIcon = ImageVector.vectorResource(id = R.drawable.ic_arrow_left)

    Row(
        modifier = Modifier.padding(10.dp),
    ) {
        IconButton(modifier = Modifier.size(25.dp, 25.dp),
            onClick = onClick) {
            Icon(
                imageVector = arrowIcon,
                contentDescription = null,
                tint = Color.Gray
            )
        }

        Text(modifier = Modifier.padding(start = 6.dp),
            text = "Profil",
            fontSize = 20.sp,
            color = Color.Gray
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilScrenPreview() {
    ProfileScreen(data = sampleProfile)
}