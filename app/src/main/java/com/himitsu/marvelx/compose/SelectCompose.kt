package com.himitsu.marvelx.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.himitsu.marvelx.data.Result
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun SelectCompose(viewModel: ViewModelMarvel, navController: NavController) {
    viewModel.getAllCharacteres()
    val listCaracteres by viewModel.marvelAllCharacteres.collectAsState()
    var characteresList: List<Result> = emptyList()

    listCaracteres.data?.let {
        characteresList = it.results
        Log.e("checkStart", "inciado listCaracteres")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 10.dp)
            .background(Color.Red)
    ) {
        Button(onClick = {
            navController.navigate("CharacteresCompose")
            viewModel.getCharacters("Thor")

        }) {
            Text(text = "Thor")

        }
        Button(onClick = {
            navController.navigate("CharacteresCompose")
            viewModel.getCharacters("Loki")
        }) {
            Text(text = "Loki")

        }


        logoMarvel()

        LazyRow(
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(characteresList) { _, personagens ->
                RowCaracterView(personagens)
            }
        }
    }
}

@Composable
fun RowCaracterView(personagens: Result) {

    val path by remember { mutableStateOf("${personagens.thumbnail.path}.${personagens.thumbnail.extension}") }
    Column(
        modifier = Modifier
            .background(Color.Red)
            .size(width = 300.dp, height = 500.dp)
    ) {


        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .size(width = 400.dp, height = 400.dp)
        ) {
            Text(
                text = personagens.name, fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            AsyncImage(
                model = path,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        }


    }
}