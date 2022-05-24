package org.d3if2132.kalkulatorbmr.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bmr")
data class BmrEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var berat: Float,
    var tinggi: Float,
    var umur: Float,
    var aktifitasFisik: String,
    var isMale: Boolean
)
