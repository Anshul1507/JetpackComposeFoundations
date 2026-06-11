package com.anshul1507.composesamplefirst.examples.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anshul1507.composesamplefirst.examples.navigation.ScreenRoute.*
import com.anshul1507.composesamplefirst.examples.ui.DashboardScreen
import com.anshul1507.composesamplefirst.examples.ui.general.BackgroundColorScreen
import com.anshul1507.composesamplefirst.examples.ui.general.ClickableScreen
import com.anshul1507.composesamplefirst.examples.ui.general.LayoutModifiersScreen
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
            SimpleTextScreen({ myNavController.popBackStack() })
        }
        composable<ClickableExample> {
            ClickableScreen({ myNavController.popBackStack() })
        }
        composable<LayoutModifiersExample> {
            LayoutModifiersScreen({ myNavController.popBackStack() })
        }
        composable<BackgroundColorExample> {
            BackgroundColorScreen({ myNavController.popBackStack() })
        }

        /**
         * STATE MANAGEMENT
         */
        composable<ProcessDeathExample> {
            ProcessDeathScreen({ myNavController.popBackStack() })
        }

    }
}