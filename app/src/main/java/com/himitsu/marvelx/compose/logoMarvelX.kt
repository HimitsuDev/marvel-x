package com.himitsu.marvelx.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.himitsu.marvelx.R

@Composable
fun LogoMarvel(){
    Box{
        Image(painterResource(id = R.drawable.marvel_x_transparent), contentDescription = null)
    }
}