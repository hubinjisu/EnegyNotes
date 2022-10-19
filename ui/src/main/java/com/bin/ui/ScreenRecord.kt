package com.bin.ui

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ScreenRecord(
    onNotesClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onRecordClicked: (waterReading: String, electricityReading: String, gasReading: String) -> Unit
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
fun RecordList(onRecordClicked: (waterReading: String, electricityReading: String, gasReading: String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var waterReading by remember { mutableStateOf("") }
        var electricityReading by remember { mutableStateOf("") }
        var gasReading by remember { mutableStateOf("") }
        OutlinedTextField(
            value = waterReading,
            onValueChange = { waterReading = it },
            label = { Text("Water") },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = electricityReading,
            onValueChange = { electricityReading = it },
            label = { Text("Electricity") },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = gasReading,
            onValueChange = { gasReading = it },
            label = { Text("Gas") },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = { onRecordClicked(waterReading, electricityReading, gasReading) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text(text = "Record")
        }
    }
}

@Composable
fun BottomButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Text(text = "Record")
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewRecordField() {
//    EnergyNotesTheme {
//        RecordList((){
//
//        })
//    }
//}