package com.himitsu.marvelx.compose.components

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun NavigationBarMarvel(viewModel: ViewModelMarvel, navController: NavController){
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Preview", "Comics", "Series")
    val iconsNavegation = listOf(Icons.Filled.Home, Icons.Filled.Face, Icons.Filled.Info, Icons.Filled.List)

    val id by viewModel.idCharactere.collectAsState()

    NavigationBar {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {Icon(iconsNavegation[index], contentDescription = item)},
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index

                    when(selectedItem) {
                        0 ->  navController.navigate("SelectCompose")
                        1 -> navController.navigate("CharacteresCompose")
                        2 -> {
                            viewModel.getComics(id)
                            navController.navigate("ComicsCompose")
                        }
                        3 ->  navController.navigate("SelectCompose")

                    }

                }
            )

        }
    }


}