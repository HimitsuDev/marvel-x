package com.himitsu.marvelx.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun checkRest(viewModel: ViewModelMarvel){
    val dataMarvel by viewModel.marvelRepository.collectAsState()

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {

        item {

            Button(onClick = {
                viewModel.getCharacters("1009664")

            }
            ) {
                Text(text = "Disparar")

            }
        }
        item{Text("Dados do Personagem", fontSize = 30.sp)}


        val listName = dataMarvel.data?.results ?: emptyList()
        val descriptMarvel = dataMarvel.attributionText ?: ""

        item{ Text(text = descriptMarvel, color = Color.Red, fontSize = 20.sp) }

        itemsIndexed(listName){_,personagem ->
            exibirName(personagem)
        }


    }
}
@Composable
fun exibirName(personagem: com.himitsu.marvelx.data.Result){
    Text(text = personagem.name, fontSize = 20.sp)
    Text(text = personagem.description, fontSize = 20.sp)

}