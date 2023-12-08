package com.himitsu.marvelx.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himitsu.marvelx.data.Characters
import com.himitsu.marvelx.network.EndPoint
import com.himitsu.marvelx.network.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ViewModelMarvel: ViewModel() {

    private val _marvelRepository = MutableStateFlow(defaultCharacters())
    var marvelRepository = _marvelRepository.asStateFlow()

    private val _marvelAllCharacteres = MutableStateFlow(defaultCharacters())
    var marvelAllCharacteres = _marvelAllCharacteres.asStateFlow()




    fun getCharacters(id: String ) = viewModelScope.launch{
        try {
            val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://gateway.marvel.com/")
            val endPoint = retrofitClient.create(EndPoint::class.java)

            withContext(Dispatchers.IO){
                suspendCoroutine  { contination ->
                    endPoint.getCharacters(id).enqueue(object : retrofit2.Callback<Characters> {
                        override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                            if (response.isSuccessful){
                                response.body()?.let {
                                    _marvelRepository.value = it
                                    Log.e("Verific", " esse é o resultado ${_marvelRepository.value.data}")


                                }
                            } else {
                                Log.e("Verific", "response voltou null ${_marvelRepository.value.data}")
                            }

                        }

                        override fun onFailure(call: Call<Characters>, t: Throwable) {
                            Log.d("Error1", t.message.toString())
                            contination.resumeWithException(t)
                        }
                    })
                }
            }

        }

        catch (e: Exception) {
            Log.d("Error2", e.message.toString())

        }

    }


    fun getAllCharacteres() = viewModelScope.launch {
        try {
            Log.d("startGet", "iniciou getlAllCharacteres")
            val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://gateway.marvel.com/")
            val endPoint = retrofitClient.create(EndPoint::class.java)

            withContext(Dispatchers.IO){
                suspendCoroutine  { contination ->
                    endPoint.getAllCharacteres().enqueue(object : retrofit2.Callback<Characters> {
                        override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                            if (response.isSuccessful){
                                response.body()?.let {
                                    _marvelAllCharacteres.value = it
                                    Log.e("Verific", " esse é o resultado ${_marvelAllCharacteres.value.data}")


                                }
                            } else {
                                Log.e("Verific", "response voltou null")
                            }

                        }

                        override fun onFailure(call: Call<Characters>, t: Throwable) {
                            Log.d("Error1", t.message.toString())
                            contination.resumeWithException(t)
                        }
                    })
                }
            }

        } catch (e: Exception){
            Log.d("Error2", e.message.toString())
        }
    }

}

fun defaultCharacters(): Characters{
    return Characters(null,
        null,
        null,
        null,
        null,null,
        null
        )
}