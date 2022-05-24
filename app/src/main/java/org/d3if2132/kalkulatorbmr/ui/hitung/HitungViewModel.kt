package org.d3if2132.kalkulatorbmr.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2132.kalkulatorbmr.db.BmrDao
import org.d3if2132.kalkulatorbmr.db.BmrEntity
import org.d3if2132.kalkulatorbmr.model.HasilBmr
import org.d3if2132.kalkulatorbmr.model.hitungBmr

class HitungViewModel(private val db: BmrDao) : ViewModel() {
    private val hasilBmr = MutableLiveData<HasilBmr?>()
    val data = db.getLastBmr()
    fun hitungBmr(
        berat: Float,
        tinggi: Float,
        umur: Float,
        selectedFA: String,
        isMale: Boolean
    ) {
        val dataBmr = BmrEntity(
            berat = berat,
            tinggi = tinggi,
            umur = umur,
            aktifitasFisik = selectedFA,
            isMale = isMale
        )
        hasilBmr.value = dataBmr.hitungBmr()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataBmr)
            }
        }
    }

    fun getHasilBmr(): LiveData<HasilBmr?> = hasilBmr
}