package com.himitsu.marvelx.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.himitsu.marvelx.R
import com.himitsu.marvelx.compose.components.FontMarvel
import com.himitsu.marvelx.compose.components.NavigationBarMarvel
import com.himitsu.marvelx.compose.components.loadCompose
import com.himitsu.marvelx.data.Result
import com.himitsu.marvelx.model.ViewModelMarvel


@Composable
fun CharacteresCompose(viewModel: ViewModelMarvel,navController: NavController) {
    selectedDate = false
    textSearch = ""
    val getCharacters by viewModel.marvelRepository.collectAsState()
    val characteres = mutableListOf<Result>()
    val loading by viewModel.loading.collectAsState()


    getCharacters.data?.results?.forEach {
        characteres.add(it)

    }

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFFFF0000), Color(0xFF9E171B))
                    )
                )
                .fillMaxSize()
        ) {
            if(!loading) {

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .padding(bottom = 80.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFFF0000), Color(0xFF9E171B))
                        )
                    )
            ) {
                item { logoMarvel() }
                itemsIndexed(characteres) { _, personagem ->
                    ViewPerson(personagem)
                }
                item { FontMarvel() }
            }
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NavigationBarMarvel(viewModel, navController)
            }

            } else {
                loadCompose()
            }
        }

}

@Composable
fun ViewPerson(personagem: Result){

    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(top = 10.dp)) {
        Text(
            text = personagem.name, fontSize = 20.sp, color = Color.White,
            modifier = Modifier.padding(bottom = 0.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .size(width = 400.dp, height = 400.dp)
        ) {
            Box {
                if(personagem.thumbnail.path != "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"){
                    AsyncImage(
                        model = "${personagem.thumbnail.path}.${personagem.thumbnail.extension}",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                } else {

                    Image(painterResource(id = R.drawable.nao_encontrado), contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds)
                }

            }
        }


        ElevatedCard(colors =  CardDefaults.cardColors(
            containerColor = Color(0xFF910B0B),
        ) ,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 5.dp, end = 5.dp)

        ){
            Text(text = personagem.description, fontSize = 20.sp, color = Color.White)
        }

    }

}
