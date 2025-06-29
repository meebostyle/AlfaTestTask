package com.example.alfatesttask.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alfatesttask.ui.theme.AlfaTestTaskTheme
import com.example.alfatesttask.ui.view.CardHistoryView
import com.example.alfatesttask.ui.view.CardInputView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val focusManager = LocalFocusManager.current
            AlfaTestTaskTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { focusManager.clearFocus() }
                            )
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "card_input_screen"
                        ) {
                            composable("card_input_screen") {
                                CardInputView(
                                    navController,
                                    focusManager
                                )
                            }
                            composable("history_screen") { CardHistoryView() }
                        }

                    }
                }
            }
        }
    }
}