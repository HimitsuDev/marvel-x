package com.himitsu.marvelx.compose

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.himitsu.marvelx.compose.components.FontMarvel
import com.himitsu.marvelx.compose.components.NavigationBarMarvel
import com.himitsu.marvelx.compose.components.loadCompose
import com.himitsu.marvelx.data.comics.Result
import com.himitsu.marvelx.model.ViewModelMarvel

var selectedDate by  mutableStateOf(false)
@Composable
fun ComicsCompose(viewModel: ViewModelMarvel, navController: NavController) {
    val getComics by viewModel.comics.collectAsState()
    val comics = mutableListOf<Result>()
    var offSet by remember { mutableIntStateOf(0) }
    val loading by viewModel.loading.collectAsState()


    Log.d("selectedDate, inicio compose", "$selectedDate")


    getComics.data?.results?.let {
        comics.addAll(it)
        offSet = getComics.data!!.offset
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

        if (!loading) {
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    item { LogoMarvel() }
                    item { SeachPerDate(viewModel, navController) }
                    item {
                        LazyRow(state = rememberLazyListState()) {
                            itemsIndexed(comics) { _, item ->
                                ViewComics(item, navController, viewModel)
                            }

                            getComics.data?.let {
                                if (it.count >= it.limit) {
                                    item { ButtomQueryPlus(viewModel, offSet, navController) }
                                }
                            }
                        }
                    }
                    item { FontMarvel() }

                }
                Box(
                    modifier = Modifier

                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter,
                ) {

                    NavigationBarMarvel(viewModel, navController)
                }
            }

        }  else {
            loadCompose()
        }
    }

}

@Composable
fun ViewComics(comics: Result, navController: NavController, viewModel: ViewModelMarvel) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .clickable {

                viewModel.getComicsDetails(comics.id)
                Log.d("idComics", comics.id.toString())
                navController.navigate("ComicsDetailsCompose")
            }
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
fun ButtomQueryPlus(viewModel: ViewModelMarvel, offSet: Int, navController: NavController) {
    val id by viewModel.idCharactere.collectAsState()
    val newOffSet = offSet + 20

    Box(modifier = Modifier
        .padding(start = 2.dp, end = 2.dp)
        .size(width = 300.dp, height = 300.dp)
        .background(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFFF0000), Color(0xFF9E171B))
            )
        )
        .clickable {
            if (!selectedDate) {
                viewModel.getComics(id, offSet = newOffSet)
                Log.d("selectedDate getComics", "$selectedDate")

            } else {
                viewModel.getComicsYear(id, offSet = newOffSet, year = textSearch)
                Log.d("selectedDate getComicsYear", "$selectedDate")

            }

            viewModel.getComicsYear(id, offSet = newOffSet, year = textSearch)
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

var textSearch by mutableStateOf("")
@Composable
fun SeachPerDate(viewModel: ViewModelMarvel, navController: NavController){
    val id by viewModel.idCharactere.collectAsState()

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment =Alignment.CenterHorizontally) {

    }
    Row(modifier = Modifier
        .padding(bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Filter by year", color = Color.White, fontSize = 16.sp,
            modifier = Modifier.padding(end = 10.dp))

        OutlinedTextField(value = textSearch.take(30), onValueChange = {
            textSearch = it },
            maxLines = 1, label = { Text(text = "YYYY", color = Color.White, modifier = Modifier
                .padding(top =1.dp)) },
            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
            modifier = Modifier
                .size(width = 100.dp, height = 60.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )

        Icon(imageVector = Icons.Default.Search,
            contentDescription = null, tint = Color.White,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    selectedDate = true
                    Log.d("_year, clicado", textSearch)
                    viewModel.getComicsYear(id, year = textSearch)
                    navController.navigate("ComicsCompose")
                    Log.d("selectedDate, clicado", "$selectedDate")
                }


        )

    }
}