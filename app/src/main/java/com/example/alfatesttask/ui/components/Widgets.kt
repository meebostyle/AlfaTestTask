package com.example.alfatesttask.ui.components

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.alfatesttask.domain.model.BinInfoModel
import com.example.alfatesttask.ui.viewmodel.CardInputViewModel
import com.example.alfatesttask.utils.calculateNewCursorPosition
import com.example.alfatesttask.utils.toCardFormat


@Composable
fun CardInputField(viewModel: CardInputViewModel) {
    OutlinedTextField(
        value = viewModel.textFieldValue.value,
        onValueChange = { newValue ->
            viewModel.clearIncorrectInput()
            val filteredText = newValue.text.filter { it.isDigit() }.take(8)
            val oldCursorPos = newValue.selection.start
            val formattedText = filteredText.toCardFormat()
            val newCursorPos = calculateNewCursorPosition(
                oldText = newValue.text,
                newText = formattedText,
                oldCursorPos = oldCursorPos
            )
            viewModel.setTextFieldValue(
                TextFieldValue(
                    text = formattedText,
                    selection = TextRange(newCursorPos)
                )
            )
        },
        isError = viewModel.incorrectInput.value,
        label = { Text("Card BIN (6-8 digits)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        trailingIcon = {
            if (viewModel.textFieldValue.value.text.isNotEmpty()) {
                IconButton(
                    onClick = { viewModel.setTextFieldValue(TextFieldValue("")) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        },
    )
}

@Composable
fun CardInfo(
    binInfoModel: BinInfoModel
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CardInfoRow("Bank", binInfoModel.bank)

            if (binInfoModel.bankUrl != "-")
                CardInfoRow("Bank url", content = {
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(0.4f),
                        text = AnnotatedString(binInfoModel.bankUrl),
                        onClick = {
                            try {
                                val intent =
                                    Intent(Intent.ACTION_VIEW, binInfoModel.bankUrl.toUri())
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Log.e("Intent Log", "$e")
                            }
                        },
                        style = TextStyle(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 12.sp
                        )
                    )
                })
            else
                CardInfoRow("Bank url", binInfoModel.bankUrl)

            if (binInfoModel.phone != "-")
                CardInfoRow("Phone", content = {
                    ClickableText(

                        text = AnnotatedString(binInfoModel.phone),
                        onClick = {
                            try {
                                val intent = Intent(
                                    Intent.ACTION_DIAL,
                                    "tel:${binInfoModel.phone}".toUri()
                                )
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Log.e("Intent Log", "$e")
                            }
                        },
                        style = TextStyle(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 12.sp
                        )
                    )
                })
            else
                CardInfoRow("Phone", binInfoModel.phone)

            CardInfoRow("City", binInfoModel.city)

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CardInfoRow("Type", binInfoModel.type)
            CardInfoRow("Country", binInfoModel.country)
            if (binInfoModel.coordinates != "-")
                CardInfoRow("Coordinates", content = {
                    ClickableText(

                        text = AnnotatedString(binInfoModel.coordinates),
                        onClick = {
                            try {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    "geo:${binInfoModel.latitude},${binInfoModel.longitude}?q=${binInfoModel.country}".toUri()
                                )
                                if (intent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(intent)
                                } else {
                                    val webIntent = Intent(
                                        Intent.ACTION_VIEW,
                                        "https://www.google.com/maps/search/?api=1&query==${binInfoModel.latitude},${binInfoModel.longitude}?q=${binInfoModel.country}".toUri()
                                    )
                                    context.startActivity(webIntent)
                                }
                            } catch (e: Exception) {
                                Log.e("Intent Log", "$e")
                            }
                        },
                        style = TextStyle(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 12.sp
                        )
                    )
                })
            else
                CardInfoRow("Coordinates", binInfoModel.coordinates)


        }

    }

}

@Composable
fun CardInfoRow(label: String, value: String) {
    Column {
        Text(text = "$label:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(modifier = Modifier.fillMaxWidth(0.4f), text = value, fontSize = 12.sp)
    }
}

@Composable
fun CardInfoRow(label: String, content: @Composable () -> Unit) {
    Column {
        Text(text = "$label:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(4.dp))
        content()
    }
}