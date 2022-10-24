package com.bin.data.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalTypeConverter {

    @TypeConverter
    fun bigDecimalToString(input: BigDecimal): String = input.toPlainString()

    @TypeConverter
    fun stringToBigDecimal(input: String): BigDecimal = if (input.isBlank()) {
        BigDecimal.valueOf(0.0)
    } else {
        input.toBigDecimalOrNull() ?: BigDecimal.valueOf(0.0)
    }
}