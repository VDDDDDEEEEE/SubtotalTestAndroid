package com.test.subtotal.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.test.subtotal.model.BooksResponse
import com.test.subtotal.utils.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface GoogleApiInstance {
    companion object {
        fun create(): GoogleApiInstance {

            val interceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
            }.build()
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(GoogleApiInstance::class.java)
        }
    }

    @GET("volumes")
    suspend fun search(
        @Query("q") text: String,
        @Query("maxResults") maxResults: String = "40",
        @Query("key") apiKey: String = Const.GOOGLE_CLOUD_KEY,
    ): Response<BooksResponse>
}