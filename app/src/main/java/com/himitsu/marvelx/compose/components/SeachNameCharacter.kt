package com.himitsu.marvelx.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun SeachNameCharacter(viewModel: ViewModelMarvel, navController: NavController){

    var textSearchDate by remember { mutableStateOf("") }

    Row(modifier = Modifier
        .padding(bottom = 15.dp)
    ){
        OutlinedTextField(value = textSearchDate.take(30), onValueChange = {
            textSearchDate = it },
            maxLines = 1, label = { Text(text = "Character", color = Color.White) },
            textStyle = TextStyle(color = Color.White, fontSize = 24.sp)
        )


        Icon(imageVector = Icons.Default.Search,
            contentDescription = null, tint = Color.White,
            modifier = Modifier
                .padding(top = 3.dp)
                .size(50.dp)
                .clickable {
                    viewModel.getCharacters(textSearchDate, navController)
                    navController.navigate("CharacteresCompose")
                }

        )

    }

}