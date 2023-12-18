package com.himitsu.marvelx.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.himitsu.marvelx.compose.alertNaoEncontrado
import com.himitsu.marvelx.data.Characters
import com.himitsu.marvelx.data.comics.ComicsData
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

    private val _comics = MutableStateFlow(defaultComics())
    var comics = _comics.asStateFlow()

    private val _idCharactere = MutableStateFlow("")
    var idCharactere = _idCharactere.asStateFlow()

    private val _loading = MutableStateFlow(false)
    var loading = _loading.asStateFlow()

    private val _comicsDatails = MutableStateFlow(defaultComics())
    var comicsDatails = _comicsDatails.asStateFlow()


    fun getCharacters(id: String, navController: NavController) = viewModelScope.launch{
        try {
            _loading.value = true
            val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://gateway.marvel.com/")
            val endPoint = retrofitClient.create(EndPoint::class.java)

            withContext(Dispatchers.IO){
                suspendCoroutine  { contination ->
                    endPoint.getCharacters(id).enqueue(object : retrofit2.Callback<Characters> {
                        override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                            if (response.isSuccessful){
                                response.body()?.let {
                                    Log.e("response", " verdadeiro, ${it.data}")
                                    if (it.data!!.results.isNotEmpty()) {
                                        _marvelRepository.value = it

                                        _idCharactere.value = it.data!!.results.first().id.toString()
                                        _loading.value = false
                                        alertNaoEncontrado = false
                                        navController.navigate("CharacteresCompose")

                                    } else {
                                        Log.e("response", "vazia, tela de nao encontrado")
                                        alertNaoEncontrado = true
                                        navController.navigate("SelectCompose")
                                    }


                                }
                            } else {
                                _loading.value = false
                                Log.e("response", "false, response voltou null")
                            }

                        }

                        override fun onFailure(call: Call<Characters>, t: Throwable) {
                            Log.d("Error1", t.message.toString())
                            contination.resumeWithException(t)
                            _loading.value = false
                        }
                    })
                }
            }

        }

        catch (e: Exception) {
            Log.d("Error2", e.message.toString())
            _loading.value = false

        }

    }


    fun getAllCharacteres() = viewModelScope.launch {
        try {
            _loading.value = true
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
                                    _loading.value = false

                                }
                            } else {
                                Log.e("Verific", "response voltou null")
                                _loading.value = false
                            }
                        }

                        override fun onFailure(call: Call<Characters>, t: Throwable) {
                            Log.d("Error1", t.message.toString())
                            contination.resumeWithException(t)
                            _loading.value = false
                        }
                    })
                }
            }

        } catch (e: Exception){
            Log.d("Error2", e.message.toString())
            _loading.value = false
        }
    }

    fun getComics(id: String, offSet: Int =0) = viewModelScope.launch {
        try {
            _loading.value = true
            val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://gateway.marvel.com/")
            val endPoint = retrofitClient.create(EndPoint::class.java)

            withContext(Dispatchers.IO){
                suspendCoroutine  { contination ->
                    endPoint.getComics(characterId = id, offset = offSet).enqueue(object : retrofit2.Callback<ComicsData> {
                        override fun onResponse(call: Call<ComicsData>, response: Response<ComicsData>) {
                            if (response.isSuccessful){
                                response.body()?.let {
                                    _comics.value = it
                                    Log.e("Verific", " esse é o resultado ${_comics.value.data}")
                                    _loading.value = false

                                }
                            } else {
                                Log.e("Verific", "response voltou null ${_comics.value.data}")
                                _loading.value = false
                            }

                        }

                        override fun onFailure(call: Call<ComicsData>, t: Throwable) {
                            Log.d("Error1", t.message.toString())
                            contination.resumeWithException(t)
                            _loading.value = false
                        }
                    })
                }
            }

        }

        catch (e: Exception) {
            Log.d("Error2", e.message.toString())
            _loading.value = false

        }
    }


    fun getComicsDetails(idComics: Int) = viewModelScope.launch {
        try {
            _loading.value = true
            val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://gateway.marvel.com/")
            val endPoint = retrofitClient.create(EndPoint::class.java)

            withContext(Dispatchers.IO){
                suspendCoroutine  { contination ->
                    endPoint.getComicsDetails(idComics).enqueue(object : retrofit2.Callback<ComicsData> {
                        override fun onResponse(call: Call<ComicsData>, response: Response<ComicsData>) {
                            if (response.isSuccessful){
                                response.body()?.let {
                                    _comicsDatails.value = it
                                    Log.e("Verific", " esse é o resultado ${_comicsDatails.value.data}")
                                    _loading.value = false

                                }
                            } else {
                                Log.e("Verific", "response voltou null ${_comicsDatails.value.data}")
                                _loading.value = false
                            }

                        }

                        override fun onFailure(call: Call<ComicsData>, t: Throwable) {
                            Log.d("Error1", t.message.toString())
                            contination.resumeWithException(t)
                            _loading.value = false
                        }
                    })
                }
            }

        }

        catch (e: Exception) {
            Log.d("Error2", e.message.toString())
            _loading.value = false

        }

    }

    fun getComicsYear(id: String, offSet: Int =0, year: String) = viewModelScope.launch {
        val cleanedYear = year.replace(Regex("[^\\d]"), "")

        try {
            Log.e("Year", "tentou buscar pelo ano")
            val newYear = cleanedYear + "-01-01," + (cleanedYear.toInt() + 1) + "-01-01"
            Log.e("Year", "$newYear")

            _loading.value = true
            val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://gateway.marvel.com/")
            val endPoint = retrofitClient.create(EndPoint::class.java)

            withContext(Dispatchers.IO){
                suspendCoroutine  { contination ->
                    endPoint.getComicsYear(characterId = id, offset = offSet, dateRange = newYear).enqueue(object : retrofit2.Callback<ComicsData> {
                        override fun onResponse(call: Call<ComicsData>, response: Response<ComicsData>) {
                            if (response.isSuccessful){
                                response.body()?.let {
                                    _comics.value = it
                                    Log.e("Verific", " esse é o resultado ${_comics.value.data}")
                                    _loading.value = false

                                }
                            } else {
                                Log.e("Verific", "response voltou null ${_comics.value.data}")
                                _loading.value = false
                            }

                        }

                        override fun onFailure(call: Call<ComicsData>, t: Throwable) {
                            Log.d("Error1", t.message.toString())
                            contination.resumeWithException(t)
                            _loading.value = false
                        }
                    })
                }
            }

        }

        catch (e: Exception) {
            Log.d("Error2 getComicsYear", e.message.toString())
            _loading.value = false

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

fun defaultComics(): ComicsData {
    return ComicsData(
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}
