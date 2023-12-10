package com.himitsu.marvelx.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun loadCompose(){
   Column(horizontalAlignment =Alignment.CenterHorizontally,
       modifier = Modifier
           .fillMaxSize()
           .background(Color.Red)) {
       Icon(imageVector = Icons.Filled.Refresh, contentDescription = null,
           tint = Color.White,
           modifier = Modifier.size(80.dp))

   }
}