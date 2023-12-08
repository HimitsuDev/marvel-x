package com.himitsu.marvelx.compose

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.himitsu.marvelx.data.Result
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun SelectCompose(viewModel: ViewModelMarvel){
    viewModel.getCharacters("Thor")
    val listCaracteres by viewModel.marvelRepository.collectAsState()
    var characteresList: List<Result> = emptyList()

    listCaracteres.data?.let {
        characteresList  = it.results
        Log.e("checkStart", "inciado listCaracteres")
    }

    LazyColumn(modifier = Modifier
            .fillMaxSize()) {
            itemsIndexed(characteresList){_, personagens ->
                rowCaracterView(personagens)
            }
    }
}
@Composable
fun rowCaracterView(personagens: Result){
    Text(text = "testandooo")
    Text(text = "testandooo")

    Text(text = "testandooo")

    Text(text = "testandooo")

    Text(text = "testandooo")

    Text(text = personagens.name, fontSize = 30.sp )
}