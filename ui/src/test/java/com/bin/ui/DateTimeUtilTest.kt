package com.bin.ui

import com.google.common.truth.Truth.assertThat
import java.time.LocalDate
import java.util.Calendar
import java.util.TimeZone
import org.junit.Test

class DateTimeUtilTest {

    private val sut = DateTimeUtil

    @Test
    fun formatZonedDateTime() {
        // given
        val dateTime = DATE_TIME

        // when
        val result = sut.formatLocalDate(dateTime)

        // then
        assertThat(result).isEqualTo(MOCK_DATE)

    }

    @Test
    fun convertToZonedDateTime() {
        // given
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.set(DATE_TIME.year, DATE_TIME.month.value - 1, DATE_TIME.dayOfMonth)
        val dateTime = calendar.timeInMillis

        // when
        val result = sut.formatMilliseconds(dateTime)

        // then
        assertThat(result).isEqualTo(MOCK_DATE)
    }

    companion object {
        private const val MOCK_DATE = "2022-09-30"
        private val DATE_TIME = LocalDate.parse(MOCK_DATE)
    }
}