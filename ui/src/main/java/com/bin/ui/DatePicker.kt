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
import com.bin.ui.DateTimeUtil.formatLocalDate
import com.bin.ui.ui.theme.EnergyNotesTheme
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.LocalDate
import java.util.Calendar
import java.util.TimeZone

@Composable
fun DatePickerView(
    datePicked: LocalDate,
    updatedDate: (date: Long) -> Unit
) {
    val activity = LocalContext.current as AppCompatActivity
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clickable {
                showDatePicker(activity, datePicked, updatedDate)
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
                    text = formatLocalDate(datePicked),
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
    datePicked: LocalDate,
    updatedDate: (Long) -> Unit
) {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    calendar.set(datePicked.year, datePicked.month.value - 1, datePicked.dayOfMonth)
    val picker = MaterialDatePicker.Builder.datePicker().setSelection(calendar.timeInMillis)
        .setTheme(R.style.MaterialCalendarTheme).build()
    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        updatedDate(it)
    }
}

@Preview
@Composable
fun DatePickerViewPreviewer() {
    EnergyNotesTheme {
        DatePickerView(LocalDate.now()) {}
    }
}