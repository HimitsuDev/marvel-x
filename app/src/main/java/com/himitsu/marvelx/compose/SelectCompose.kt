package com.himitsu.marvelx.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.himitsu.marvelx.compose.components.FontMarvel
import com.himitsu.marvelx.compose.components.SeachNameCharacter
import com.himitsu.marvelx.data.sample.SampleCharacteres
import com.himitsu.marvelx.data.sample.getlistSampleCharacteres
import com.himitsu.marvelx.model.ViewModelMarvel

var alertNaoEncontrado by mutableStateOf(false)
@Composable
fun SelectCompose(viewModel: ViewModelMarvel, navController: NavController) {
    selectedDate = false
    textSearch = ""

    val listSampleCharacteres = getlistSampleCharacteres()



    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFF0000), Color(0xFF9E171B))
                )
            )
    ) {

        item { logoMarvel() }
        item{ SeachNameCharacter(viewModel, navController) }

        item{
            if (alertNaoEncontrado) {
                Text(
                    text = "Not found", fontSize = 24.sp, color = Color.Cyan,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }

        item{
            LazyRow {
                itemsIndexed(listSampleCharacteres) { _, personagens ->
                    RowCaracterView(personagens, navController, viewModel)
                }

            }
        }
        item { FontMarvel() }


    }
}

@Composable
fun RowCaracterView(personagens: SampleCharacteres, navController: NavController, viewModel: ViewModelMarvel) {

    val path by remember { mutableStateOf("${personagens.thumbnail.path}.${personagens.thumbnail.extension}") }
    Column(
        modifier = Modifier
            .clickable {
                viewModel.getCharacters(personagens.name, navController)
            }
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFF0000), Color(0xFF9E171B))
                )
            )
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