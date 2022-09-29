package com.bin.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.bin.presentation.DataViewModel
import com.bin.presentation.model.Message
import com.bin.ui.ui.theme.EnergyNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnergyNotesActivity : ComponentActivity() {

    private val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnergyNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MessageCard(msg = Message("Bin", "Hallo World!"))
                    Conversation(viewModel)
                }
            }
        }
    }
}