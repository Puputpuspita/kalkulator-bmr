package org.d3if2132.kalkulatorbmr.ui

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2132.kalkulatorbmr.model.HasilBmr

class MainViewModel : ViewModel() {
    private val hasilBmr = MutableLiveData<HasilBmr?>()

    fun hitungBmr(
        berat: Float,
        tinggi: Float,
        umur: Float,
        isMale: Boolean,
        sedentary: Boolean,
        lighlyActive: Boolean,
        moderatelyActive: Boolean,
        veryActive: Boolean,
        extremeActive: Boolean
    ) {

        val bmr = if (isMale) {
            (10 * berat) + (6.25 * tinggi) - (5 * umur) + 5
        } else {
            (10 * berat) + (6.25 * tinggi) - (5 * umur) - 161
        }

        val tdee = if (isMale) {
            when {
                sedentary -> {
                    1.2 * bmr
                }
                lighlyActive -> {
                    1.375 * bmr
                }
                moderatelyActive -> {
                    1.55 * bmr
                }
                veryActive -> {
                    1.725 * bmr
                }
                extremeActive -> {
                    1.9 * bmr
                }
                else -> bmr
            }

        } else {
            when {
                sedentary -> {
                    1.2 * bmr
                }
                lighlyActive -> {
                    1.375 * bmr
                }
                moderatelyActive -> {
                    1.55 * bmr
                }
                veryActive -> {
                    1.725 * bmr
                }
                extremeActive -> {
                    1.9 * bmr
                }
                else -> bmr
            }
        }
        hasilBmr.value = HasilBmr(bmr.toFloat(), tdee.toFloat())
    }

    fun getHasilBmr(): LiveData<HasilBmr?> = hasilBmr
}