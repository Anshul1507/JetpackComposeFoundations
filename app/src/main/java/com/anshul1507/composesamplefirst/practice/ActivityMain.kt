package com.anshul1507.composesamplefirst.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.anshul1507.composesamplefirst.practice.navigation.AppNavGraph
import com.anshul1507.composesamplefirst.practice.ui.theme.AppTheme

class ActivityMain: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enables drawing under the status bar and navigation bar natively
        enableEdgeToEdge()

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    AppNavGraph(navController)
                }
            }
        }
    }

}