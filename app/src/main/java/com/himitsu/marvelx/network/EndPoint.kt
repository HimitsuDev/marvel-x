package com.himitsu.marvelx.network

import com.himitsu.marvelx.KeysRep.KeysRep
import com.himitsu.marvelx.data.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoint {


    //TODO alterar a Key
    @GET("v1/public/characters")
    fun getCharacters(@Query("name") name: String,
                      @Query("ts") ts: Long = KeysRep.ts,
                      @Query("apikey") apikey: String = KeysRep.apikey,
                      @Query("hash") hash: String = KeysRep.hash): Call<Characters>


    @GET("v1/public/characters")
    fun getAllCharacteres(@Query("ts") ts: Long = KeysRep.ts,
                          @Query("apikey") apikey: String = KeysRep.apikey,
                          @Query("hash") hash: String = KeysRep.hash): Call<Characters>


}