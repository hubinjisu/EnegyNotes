package com.bin.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bin.presentation.model.EnergyNoteView
import com.bin.presentation.model.EnergyTypeView
import com.bin.ui.DateTimeUtil.formatLocalDate
import com.bin.ui.ui.theme.EnergyNotesTheme
import java.time.LocalDate

@Composable
fun NoteCard(noteView: EnergyNoteView) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            Image(
                painter = painterResource(id = getEnergyIcon(noteView.energyType)),
                contentDescription = "Test Android",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Column(
                Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatLocalDate(noteView.recordDate),
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                    )
                    Text(
                        text = noteView.reading.toString(),
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Row(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = noteView.totalCost,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2,
                    )
                    Text(
                        text = noteView.cost,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
        }
    }
}

private fun getEnergyIcon(energyTypeView: EnergyTypeView) = when (energyTypeView) {
    EnergyTypeView.WATER -> R.drawable.ic_water
    EnergyTypeView.ELECTRICITY -> R.drawable.ic_electric
    EnergyTypeView.GAS -> R.drawable.ic_gas
}


@Preview(name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun DefaultPreview() {
    EnergyNotesTheme {
        NoteCard(
            noteView = EnergyNoteView(
                100,
                LocalDate.parse("2022-09-30"),
                EnergyTypeView.WATER,
                "10 $",
                "100 $"
            )
        )
    }
}