package com.example.bkmapp.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bkmapp.R


@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AlertDialogConstruction(title: String, text: String, changeScreen: () -> Unit) {
    val openDialog = remember { mutableStateOf(true)}

    if(openDialog.value){
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                changeScreen()
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    changeScreen()
                }) {
                    TextOnPrimaryContainer(text = "Close")
                }
            },
            title = {
                TextOnPrimaryContainer(text = title)
            },
            text = {
                TextOnPrimaryContainer(text = text)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_code),
                    contentDescription = "ic_code",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 2.dp
        )
    }
}

@Composable
fun TextOnPrimaryContainer(text: String){
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}