package com.anshul1507.composesamplefirst.practice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anshul1507.composesamplefirst.practice.navigation.ScreenRoute.*
import com.anshul1507.composesamplefirst.practice.ui.screens.DashboardScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.AnimationBasicsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.BackgroundColorScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.BoxStackingScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.ClickableScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.EdgeToEdgeScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.LayoutModifiersScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.SimpleTextScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.ThemeModeScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.ProcessDeathScreen


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
        composable<BoxStackingExample> {
            BoxStackingScreen({ myNavController.popBackStack() })
        }
        composable<AnimationBasicsExample> {
            AnimationBasicsScreen({ myNavController.popBackStack() })
        }
        composable<ThemeModeExample> {
            ThemeModeScreen({ myNavController.popBackStack() })
        }
        composable<EdgeToEdgeExample> {
            EdgeToEdgeScreen({ myNavController.popBackStack() })
        }

        /**
         * STATE MANAGEMENT
         */
        composable<ProcessDeathExample> {
            ProcessDeathScreen({ myNavController.popBackStack() })
        }

    }
}