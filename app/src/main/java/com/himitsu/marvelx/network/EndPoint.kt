package com.himitsu.marvelx.network

import com.himitsu.marvelx.KeysRep.KeysRep
import com.himitsu.marvelx.data.Characters
import com.himitsu.marvelx.data.comics.ComicsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoint {


    //TODO alterar a Key
    @GET("v1/public/characters")
    fun getCharacters(@Query("name") name: String,
                      @Query("ts") ts: Long = KeysRep.generateTimestamp(),
                      @Query("apikey") apikey: String = KeysRep.getApikey(),
                      @Query("hash") hash: String = KeysRep.getHash()): Call<Characters>


    @GET("v1/public/characters")
    fun getAllCharacteres(@Query("ts") ts: Long = KeysRep.generateTimestamp(),
                          @Query("apikey") apikey: String = KeysRep.getApikey(),
                          @Query("hash") hash: String = KeysRep.getHash()): Call<Characters>



    @GET("/v1/public/characters/{characterId}/comics")
    fun getComics(@Path("characterId")characterId: String,
                  @Query("limit") limit: Int = 21,
                  @Query("offset") offset: Int = 0,
                  @Query("ts") ts: Long = KeysRep.generateTimestamp(),
                  @Query("apikey") apikey: String = KeysRep.getApikey(),
                  @Query("hash") hash: String = KeysRep.getHash()): Call<ComicsData>


    @GET("/v1/public/comics/{comicId}")
    fun getComicsDetails(@Path("comicId")characterId: Int,
                         @Query("ts") ts: Long = KeysRep.generateTimestamp(),
                         @Query("apikey") apikey: String = KeysRep.getApikey(),
                         @Query("hash") hash: String = KeysRep.getHash()): Call<ComicsData>


    @GET("/v1/public/characters/{characterId}/comics")
    fun getComicsYear(@Path("characterId")characterId: String,
                  @Query("dateRange") dateRange: String,
                  @Query("limit") limit: Int = 21,
                  @Query("offset") offset: Int = 0,
                      @Query("ts") ts: Long = KeysRep.generateTimestamp(),
                      @Query("apikey") apikey: String = KeysRep.getApikey(),
                      @Query("hash") hash: String = KeysRep.getHash()): Call<ComicsData>

}