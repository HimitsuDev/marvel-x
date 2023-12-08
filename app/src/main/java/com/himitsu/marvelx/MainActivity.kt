package com.himitsu.marvelx

import android.media.audiofx.LoudnessEnhancer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.himitsu.marvelx.compose.checkRest
import com.himitsu.marvelx.model.ViewModelMarvel
import com.himitsu.marvelx.ui.theme.MARVELXTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.himitsu.marvelx.compose.SelectCompose


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<ViewModelMarvel>()


        setContent {
            MARVELXTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppHost(navController, viewModel)

                }
            }

    }

    }
}
@Composable
fun MyAppHost(navHostController: NavHostController = rememberNavController(),
              viewModel: ViewModelMarvel,
              startDestination: String = "SelectCompose"){

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable("SelectCompose"){ SelectCompose(viewModel)}
        composable("checkRest"){ checkRest(viewModel)}

    }
}

