package org.d3if2132.kalkulatorbmr.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2132.kalkulatorbmr.model.Makanan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface MakananApiService {
    @GET("static-api-list-makanan.json")
    suspend fun getHewan(): List<Makanan>
}
