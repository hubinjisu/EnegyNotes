package com.bin.data.converter

import androidx.room.TypeConverter
import com.bin.domain.model.EnergyType

class EnergyTypeConverter {
    @TypeConverter
    fun energyTypeToString(energyType: EnergyType): String = energyType.name

    @TypeConverter
    fun stringToEnergyType(energyTypeValue: String): EnergyType = EnergyType.valueOf(energyTypeValue)
}