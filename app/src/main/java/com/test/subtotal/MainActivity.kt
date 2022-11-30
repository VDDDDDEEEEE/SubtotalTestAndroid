package com.test.subtotal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.test.subtotal.ui.navigation.Graph
import com.test.subtotal.ui.navigation.RootNavigationGraph
import com.test.subtotal.ui.theme.SubtotalTestAndroidTheme
import com.test.subtotal.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startDestination = Graph.HOME
        setContent {
            SubtotalTestAndroidTheme {
                RootNavigationGraph(navController = rememberNavController(), mainViewModel = mainViewModel, startDestination = startDestination)
                // A surface container using the 'background' color from the theme
            }
        }
    }
}