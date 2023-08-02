package com.example.bkmapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bkmapp.ui.compose.AlertDialogConstruction
import com.example.bkmapp.ui.compose.HomeScreen
import com.example.bkmapp.ui.theme.BkmappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BkmappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivityNavigator()
                }
            }
        }
    }
}

@Composable
fun MainActivityNavigator(){

    val navController = rememberNavController()

    NavHost(navController, startDestination = "HomeScreen"){

        composable("HomeScreen") {
            HomeScreen(navController)
        }
        composable("UnderConstruction") {
            AlertDialogConstruction(title = "Coming Soon", text = "This feature is under construction!"){
                navController.navigate("HomeScreen")
            }
        }
    }
}