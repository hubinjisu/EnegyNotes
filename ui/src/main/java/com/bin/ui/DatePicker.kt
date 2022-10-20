package com.bin.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bin.ui.DateTimeUtil.formatRecordTime
import com.bin.ui.ui.theme.EnergyNotesTheme
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.ZonedDateTime

@Composable
fun DatePickerView(
    datePicked: ZonedDateTime?,
    updatedDate: (date: Long?) -> Unit
) {
    val activity = LocalContext.current as AppCompatActivity
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clickable {
                showDatePicker(activity, updatedDate)
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = datePicked?.let { formatRecordTime(it) } ?: "Date Picker",
                    color = MaterialTheme.colors.onSurface
                )

                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp, 20.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }

}

private fun showDatePicker(
    activity: AppCompatActivity,
    updatedDate: (Long?) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        updatedDate(it)
    }
}

@Preview
@Composable
fun DatePickerViewPreviewer() {
    EnergyNotesTheme {
        DatePickerView(null) {}
    }
}