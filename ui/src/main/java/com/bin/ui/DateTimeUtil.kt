package com.bin.ui

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

object DateTimeUtil {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun formatLocalDate(localDate: LocalDate): String =
        localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    fun formatMilliseconds(milliseconds: Long): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.format(milliseconds)

    fun toLocalDate(localDate: String): LocalDate =
        LocalDate.parse(localDate, formatter)
}