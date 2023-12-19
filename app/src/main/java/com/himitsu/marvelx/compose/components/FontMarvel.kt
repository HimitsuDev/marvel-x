package com.himitsu.marvelx.compose.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FontMarvel(){
    Text(text = "Data provided by Marvel. © 2023 MARVEL",
        fontSize = 12.sp, color = Color.White, modifier = Modifier
            .padding(top = 10.dp))
}