package org.d3if2132.kalkulatorbmr.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2132.kalkulatorbmr.model.Makanan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" + "Puputpuspita/kalkulator-bmr/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MakananApiService {
    @GET("static-api-makanan.json")
    suspend fun getHewan(): List<Makanan>
}

object MakananApi {
    val service: MakananApiService by lazy {
        retrofit.create(MakananApiService::class.java)
    }

    fun getMakananUrl(nama: String): String {
        return "$BASE_URL$nama.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }