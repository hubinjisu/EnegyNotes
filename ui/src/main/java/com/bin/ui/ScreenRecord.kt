package com.bin.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bin.presentation.model.EnergyTypeView
import com.bin.ui.DateTimeUtil.formatMilliseconds
import com.bin.ui.DateTimeUtil.toLocalDate
import com.bin.ui.ui.theme.EnergyNotesTheme
import java.time.LocalDate

@Composable
fun ScreenRecord(
    onNotesClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onRecordClicked: (String, String, String, LocalDate) -> Unit
) {
    CustomScaffold(
        title = R.string.screen_record,
        onNotesClicked = onNotesClicked,
        onRecordClicked = {},
        onAboutClicked = onAboutClicked
    ) {
        RecordList(onRecordClicked)
    }
}

@Composable
fun RecordList(onRecordClicked: (String, String, String, LocalDate) -> Unit) {
    Column(
        Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var waterReading by remember { mutableStateOf("") }
        var electricityReading by remember { mutableStateOf("") }
        var gasReading by remember { mutableStateOf("") }
        var datePicked: LocalDate by remember { mutableStateOf(LocalDate.now()) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val updateDate = { date: Long -> datePicked = toLocalDate(formatMilliseconds(date)) }
            DatePickerView(datePicked, updateDate)

            OutlinedTextField(
                value = waterReading,
                onValueChange = { waterReading = it },
                label = { Text(EnergyTypeView.WATER.name) },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = electricityReading,
                onValueChange = { electricityReading = it },
                label = { Text(EnergyTypeView.ELECTRICITY.name) },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = gasReading,
                onValueChange = { gasReading = it },
                label = { Text(EnergyTypeView.GAS.name) },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        Button(
            onClick = { onRecordClicked(waterReading, electricityReading, gasReading, datePicked) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text(text = "Record")
        }
    }
}

@Composable
fun RecordItem(itemName: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(itemName) },
        maxLines = 1,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRecordField() {
    EnergyNotesTheme {
        RecordList(onRecordClicked = { _, _, _, _ -> })
    }
}