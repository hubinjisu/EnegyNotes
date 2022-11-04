package com.bin.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class EnergyNoteRemoteEntity(
    val id: String? = null,
    val reading: Long? = null,
    val recordDate: String? = null,
    val type: String? = null,
    val userId: Long? = null
)
