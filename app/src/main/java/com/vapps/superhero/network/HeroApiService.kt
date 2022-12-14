package com.vapps.superhero.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vapps.superhero.data.dto.HeroesDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

private const val BASE_URL = "https://gateway.marvel.com"
private val TIMESTAMP = Timestamp(System.currentTimeMillis()).time.toString()
private const val PUBLIC_KEY = "4d100d8f32faab4dbf7ccee7e0c08063"
private const val PRIVATE_KEY = "23dd8794ca7aaa71dd4ae7a9420b38ca2d75454e"
const val LIMIT = "100"
private const val OFFSET = "500"

fun hash(): String {
    val input = "$TIMESTAMP$PRIVATE_KEY$PUBLIC_KEY"
    val md5 = MessageDigest.getInstance("MD5")
    return BigInteger(1, md5.digest(input.toByteArray())).toString(16).padStart(32, '0')
}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HeroApiService {
    @GET("/v1/public/characters")
    suspend fun getAllHeroes(
        @Query("apikey")apikey: String = PUBLIC_KEY,
        @Query("ts")ts: String = TIMESTAMP,
        @Query("hash")hash: String = hash(),
        @Query("limit")limit: String = LIMIT,
        @Query("offset")offset: String,
    ): HeroesDTO
}

object HeroApi {
    val retrofitService : HeroApiService by lazy {
        retrofit.create(HeroApiService::class.java)
    }
}


