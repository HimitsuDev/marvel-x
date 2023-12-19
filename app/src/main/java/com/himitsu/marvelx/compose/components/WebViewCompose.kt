package com.himitsu.marvelx.compose.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.himitsu.marvelx.model.ViewModelMarvel

@Composable
fun WebViewCompose(navController: NavController, viewModel: ViewModelMarvel) {
    val context = LocalContext.current

    LazyColumn {
        item {
            AndroidView(
                modifier = Modifier
                    .background(Color.Black),

                factory = {
                    WebView(context).apply {
                        webViewClient = WebViewClient()

                        loadUrl(viewModel.getLink())
                    }
                })
        }
    }

}