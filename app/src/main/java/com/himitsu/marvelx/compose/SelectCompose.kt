package com.himitsu.marvelx.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.himitsu.marvelx.data.Result
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun SelectCompose(viewModel: ViewModelMarvel) {
    viewModel.getAllCharacteres()
    val listCaracteres by viewModel.marvelAllCharacteres.collectAsState()
    var characteresList: List<Result> = emptyList()

    listCaracteres.data?.let {
        characteresList = it.results
        Log.e("checkStart", "inciado listCaracteres")
    }

    Column(
        modifier = Modifier
            .background(Color.Red)
    ) {
        logoMarvel()

        LazyRow(
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(characteresList) { _, personagens ->
                rowCaracterView(personagens)
            }
        }
    }
}

@Composable
fun rowCaracterView(personagens: Result) {

    val path by remember { mutableStateOf("${personagens.thumbnail.path}.${personagens.thumbnail.extension}") }
    Column(
        modifier = Modifier
            .background(Color.Red)
            .size(width = 300.dp, height = 500.dp)
    ) {

        Text(
            text = personagens.name, fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Box(
            modifier = Modifier
                .size(width = 400.dp, height = 400.dp)
                .padding(end = 10.dp)
                .background(Color.LightGray)
        ) {
            AsyncImage(
                model = path,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Text(text = personagens.description, fontSize = 22.sp)
    }
}