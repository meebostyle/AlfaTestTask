package com.example.alfatesttask.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.alfatesttask.ui.components.CardInfo
import com.example.alfatesttask.ui.components.CardInputField
import com.example.alfatesttask.ui.viewmodel.CardInputViewModel
import com.example.alfatesttask.utils.toInputString

@Composable
fun CardInputView(navController: NavController, focusManager: FocusManager) {
    val viewModel: CardInputViewModel = viewModel()
    val binInfoModel by viewModel.content.collectAsStateWithLifecycle()

    if (!viewModel.isLoading.value) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { focusManager.clearFocus() }
                ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CardInputField(viewModel)
                CardInfo(binInfoModel = binInfoModel)

                Button(
                    modifier = Modifier
                        .padding(top = 24.dp),
                    onClick = {
                        viewModel.getContent(viewModel.textFieldValue.value.text.toInputString())
                    }) {
                    Text("Check")
                }
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = {
                navController.navigate("history_screen")
            }) {
                Text("Response history")
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

    }

}

