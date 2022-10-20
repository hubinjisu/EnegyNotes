package com.bin.ui

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {

    fun formatRecordTime(recordTime: ZonedDateTime): String =
        recordTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))

    fun convertToZonedDateTime(dateTime: Long): ZonedDateTime =
        ZonedDateTime.ofInstant(Instant.ofEpochSecond(dateTime), ZoneId.systemDefault())
}