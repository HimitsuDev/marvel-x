package com.himitsu.marvelx.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.himitsu.marvelx.R

@Composable
fun loadCompose(){
   Column(horizontalAlignment =Alignment.CenterHorizontally,
       modifier = Modifier
           .padding(top = 100.dp)
           .fillMaxSize()
           .background(brush = Brush.radialGradient(
               colors= listOf(Color(0xFFFF0000), Color(0xFF9E171B)))
           )
   ) {
       Icon(imageVector = Icons.Filled.Refresh, contentDescription = null,
           tint = Color.White,
           modifier = Modifier.size(80.dp))

       Image(painterResource(id = R.drawable.marvel_x_transparent) , contentDescription = null)

   }
}