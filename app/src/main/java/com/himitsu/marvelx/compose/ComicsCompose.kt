package com.himitsu.marvelx.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.himitsu.marvelx.compose.components.NavigationBarMarvel
import com.himitsu.marvelx.compose.components.loadCompose
import com.himitsu.marvelx.data.comics.Result
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun ComicsCompose(viewModel: ViewModelMarvel, navController: NavController) {
    val getComics by viewModel.comics.collectAsState()
    val comics = mutableListOf<Result>()
    var offSet by remember { mutableStateOf(0) }
    val loading by viewModel.loading.collectAsState()




    getComics.data?.results?.let {
        comics.addAll(it)
        offSet = getComics.data!!.offset
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if(!loading){
        LazyVerticalGrid(
            verticalArrangement = Arrangement.Top,
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier
                .padding(bottom = 80.dp)
                .wrapContentSize()
                .background(Color.Red)


        ) {
            itemsIndexed(comics) { index, item ->
                viewComics(item)

            }
            item { buttomQueryPlus(viewModel, offSet, navController) }

        }
        } else{
            loadCompose()
        }




        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {

            NavigationBarMarvel(viewModel, navController)

        }


    }


}

@Composable
fun viewComics(comics: Result) {
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
}

@Composable
fun buttomQueryPlus(viewModel: ViewModelMarvel, offSet: Int, navController: NavController) {
    val id by viewModel.idCharactere.collectAsState()
    val newOffSet = offSet + 20

    Box(modifier = Modifier
        .padding(start = 2.dp, end = 2.dp)
        .size(width = 300.dp, height = 300.dp)
        .background(Color(0xFFF81D67))
        .clickable {
            viewModel.getComics(id, offSet = newOffSet)
            navController.navigate("ComicsCompose")
        }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {

                Text(text = "View more",
                fontSize = 30.sp, color = Color.White, textAlign = TextAlign.Center)

            Icon(imageVector = Icons.Filled.Refresh, contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(50.dp))
        }


    }

}