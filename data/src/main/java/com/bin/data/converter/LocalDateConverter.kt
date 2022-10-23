/*
 * Copyright (c) 2020, DB Vertrieb GmbH, Mobiles Terminal
 */

package com.bin.data.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter private constructor() {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        @TypeConverter
        @JvmStatic
        fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it, formatter) }

        @TypeConverter
        @JvmStatic
        fun fromLocalDate(date: LocalDate?) = date?.format(formatter)
    }
}