/*
 * Copyright (c) 2020, DB Vertrieb GmbH, Mobiles Terminal
 */

package com.bin.data.converter

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ZonedDateTimeConverter private constructor() {
    companion object {
        private val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

        @TypeConverter
        @JvmStatic
        fun toZonedDateTime(value: String?): ZonedDateTime? = value?.let { formatter.parse(value, ZonedDateTime::from) }

        @TypeConverter
        @JvmStatic
        fun fromZonedDateTime(date: ZonedDateTime?) = date?.format(formatter)
    }
}