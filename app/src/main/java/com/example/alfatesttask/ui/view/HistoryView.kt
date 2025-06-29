package com.example.alfatesttask.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alfatesttask.ui.components.CardInfo
import com.example.alfatesttask.ui.viewmodel.HistoryViewModel
import com.example.alfatesttask.utils.formatToString
import com.example.alfatesttask.utils.toCardFormat


@Composable
fun CardHistoryView() {
    val viewModel: HistoryViewModel = viewModel()
    val binInfoModel by viewModel.content.collectAsStateWithLifecycle()
    if (!viewModel.isLoading.value) {
        if (binInfoModel.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(binInfoModel) { pos, item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "BIN: ${item.bin.toCardFormat()}",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            )
                            CardInfo(binInfoModel = item)
                            Text(
                                text = "Date: ${item.dateTime!!.formatToString()}",
                                fontSize = 12.sp
                            )
                        }


                    }
                }

            }
        } else {
            Box(contentAlignment = Alignment.TopCenter) {
                Text(text = "Пока история пуста")
            }
        }
    } else
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }


}