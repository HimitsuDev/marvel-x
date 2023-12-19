package com.himitsu.marvelx.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.himitsu.marvelx.compose.components.FontMarvel
import com.himitsu.marvelx.compose.components.NavigationBarMarvel
import com.himitsu.marvelx.compose.components.loadCompose
import com.himitsu.marvelx.data.comics.Result
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun ComicsDetailsCompose(viewModel: ViewModelMarvel, navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFFF0000), Color(0xFF9E171B))
            )
        )
    ){
        val getComicsDetails by viewModel.comicsDatails.collectAsState()
        var comicsDetails = mutableListOf<Result>()
        val loading by viewModel.loading.collectAsState()


        getComicsDetails.data?.results?.let{
            comicsDetails.addAll(it)
        }


        //
        if(!loading) {

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFFFF0000), Color(0xFF9E171B))
                    )
                )
                .fillMaxSize()
        ){
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(comicsDetails){_, comics ->
                    comicsDetailsView(comics, viewModel, navController)
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


        } } else {
            loadCompose()
    }



    }
}
@Composable
fun comicsDetailsView(comics: Result, viewModel: ViewModelMarvel,
                      navController: NavController){

    val creatorDetails = comics.creators.items
    val characters = comics.characters.items

    var textObject = ""
    if(comics.textObjects.isNotEmpty()){
        textObject = comics.textObjects[0].text
    }





    Column(modifier = Modifier
        .padding(start = 40.dp, end = 10.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = comics.title, fontSize = 30.sp, color = Color.White)
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .size(width = 300.dp, height = 300.dp)
        ) {
            AsyncImage(
                model = "${comics.thumbnail.path}.${comics.thumbnail.extension}",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Text(text = textObject, fontSize = 24.sp, color = Color.White)

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center){
                Text(text = "Participants",
                    textAlign = TextAlign.Center, fontSize = 24.sp)
            }
        }
        for(charactere in characters){

            Text(text = charactere.name, fontSize = 24.sp, color = Color.White,
                modifier = Modifier.clickable {
                    viewModel.getCharacters(charactere.name, navController)
                    navController.navigate("CharacteresCompose")
                },
                textDecoration = TextDecoration.Underline)
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center){
                Text(text = "Creators",
                    textAlign = TextAlign.Center, fontSize = 24.sp)
            }

        }

        for (creator in creatorDetails){
            Text(text = "${creator.role} : ${creator.name}",
                fontSize = 18.sp, color = Color.White
                , overflow = TextOverflow.Ellipsis
            )
        }


        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center){
                Text(text = "Marvel.com",
                    textAlign = TextAlign.Center, fontSize = 24.sp)
            }
        }
        Column(horizontalAlignment = Alignment.Start){
            for (links in comics.urls) {
                Row{
                    Text(text = links.type + ":", color = Color.White, fontSize = 20.sp)
                    Text(text = "Redirection to marvel.com", color = Color.White, fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .clickable {
                            viewModel.setLink(links.url)
                            navController.navigate("WebViewCompose")
                        })
                }

            }
        }

    }
}