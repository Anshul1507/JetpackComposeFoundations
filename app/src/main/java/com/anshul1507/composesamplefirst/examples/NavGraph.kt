package com.anshul1507.composesamplefirst.examples

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anshul1507.composesamplefirst.examples.ScreenRoute.*
import com.anshul1507.composesamplefirst.examples.ui.DashboardScreen
import com.anshul1507.composesamplefirst.examples.ui.general.SimpleTextScreen
import com.anshul1507.composesamplefirst.examples.ui.stateManagement.ProcessDeathScreen


@Composable
fun AppNavGraph(myNavController: NavHostController) {
    NavHost(
        navController = myNavController,
        startDestination = Dashboard
    ) {
        //Master Dashboard List Screen
        composable<Dashboard> {
            DashboardScreen(
                onFeatureClick = { destination ->
                    myNavController.navigate(destination)
                }
            )
        }

        //Individual Feature Screens
        /**
         * GENERAL
         */
        composable<SimpleTextExample> {
            SimpleTextScreen(
                onBack = {
                    myNavController.popBackStack()
                }
            )
        }

        /**
         * STATE MANAGEMENT
         */
        composable<ProcessDeathExample> {
            ProcessDeathScreen(
                onBack = {
                    myNavController.popBackStack()
                }
            )
        }

    }
}