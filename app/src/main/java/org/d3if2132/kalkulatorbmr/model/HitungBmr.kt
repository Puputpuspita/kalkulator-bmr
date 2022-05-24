package org.d3if2132.kalkulatorbmr.model

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2132.kalkulatorbmr.db.BmrEntity

fun BmrEntity.hitungBmr(): HasilBmr {
    val bmr = if (isMale) {
        (10 * berat) + (6.25 * tinggi) - (5 * umur) + 5
    } else {
        (10 * berat) + (6.25 * tinggi) - (5 * umur) - 161
    }

    //  selectedFA == "sedikit beraktivitas, tidak berolahraga",
    // selectedFA == "olahraga ringan 1 – 3 kali dalam seminggu",
    // selectedFA == "olahraga ringan 6 - 7 kali dalam seminggu",
    // selectedFA == "olahraga berat 1 atau 2 kali dalam sehari",
    // selectedFA == "olahraga berat 2 kali atau lebih dalam sehari",

    val tdee = if (isMale) {
        when {
            aktifitasFisik == "sedikit beraktivitas, tidak berolahraga" -> {
                1.2 * bmr
            }
            aktifitasFisik == "olahraga ringan 1 – 3 kali dalam seminggu" -> {
                1.375 * bmr
            }
            aktifitasFisik == "olahraga ringan 6 - 7 kali dalam seminggu" -> {
                1.55 * bmr
            }
            aktifitasFisik == "olahraga berat 1 atau 2 kali dalam sehari" -> {
                1.725 * bmr
            }
            aktifitasFisik == "olahraga berat 2 kali atau lebih dalam sehari" -> {
                1.9 * bmr
            }
            else -> bmr
        }

    } else {
        when {
            aktifitasFisik == "sedikit beraktivitas, tidak berolahraga" -> {
                1.2 * bmr
            }
            aktifitasFisik == "olahraga ringan 1 – 3 kali dalam seminggu" -> {
                1.375 * bmr
            }
            aktifitasFisik == "olahraga ringan 6 - 7 kali dalam seminggu" -> {
                1.55 * bmr
            }
            aktifitasFisik == "olahraga berat 1 atau 2 kali dalam sehari" -> {
                1.725 * bmr
            }
            aktifitasFisik == "olahraga berat 2 kali atau lebih dalam sehari" -> {
                1.9 * bmr
            }
            else -> bmr
        }
    }
    return HasilBmr(bmr.toFloat(), tdee.toFloat())
}