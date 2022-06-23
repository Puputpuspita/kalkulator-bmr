package org.d3if2132.kalkulatorbmr.ui.rekomendasi

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2132.kalkulatorbmr.MainActivity
import org.d3if2132.kalkulatorbmr.model.Makanan
import org.d3if2132.kalkulatorbmr.network.ApiStatus
import org.d3if2132.kalkulatorbmr.network.MakananApi
import org.d3if2132.kalkulatorbmr.network.UpdateWorker
import java.util.concurrent.TimeUnit

class RekomendasiViewModel: ViewModel() {
    private val data = MutableLiveData<List<Makanan>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(MakananApi.service.getHewan())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("RekomendasiViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Makanan>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(2, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}