package com.mgarma.jadkuls2.data

import com.mgarma.jadkuls2.network.CollegeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val jadkulRepository: JadkulRepository
}

private var json = Json {
    ignoreUnknownKeys = true
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://strapi-prod.garma76.my.id/api/"

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .build()
        chain.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType())
//            Json.asConverterFactory("application/json".toMediaType())
        )
        .build()

    private val retrofitService: CollegeApiService by lazy {
        retrofit.create(CollegeApiService::class.java)
    }

    override val jadkulRepository: JadkulRepository by lazy {
        DefaultJadkulRepository(retrofitService)
    }
}