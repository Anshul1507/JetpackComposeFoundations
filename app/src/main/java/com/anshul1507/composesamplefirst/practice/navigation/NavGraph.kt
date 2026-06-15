package com.anshul1507.composesamplefirst.practice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anshul1507.composesamplefirst.practice.navigation.ScreenRoute.*
import com.anshul1507.composesamplefirst.practice.ui.screens.DashboardScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.MaterialComponentsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.AnimationBasicsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.BackgroundColorScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.BoxStackingScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.ClickableScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.EdgeToEdgeScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.LayoutModifiersScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.SimpleTextScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.ThemeModeScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.BackHandlerScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.FlowCollectionScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.LocalStateScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.SaveableStateScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.SideEffectsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.StateHoistingScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.ViewModelLiveScreen


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
        setGeneralNavGraphs(myNavController)
        setStateManagementNavGraphs(myNavController)
        setComponentsNavGraphs(myNavController)
    }
}

private fun NavGraphBuilder.setComponentsNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<MaterialComponentsExample> { MaterialComponentsScreen(::onBack) }
}

private fun NavGraphBuilder.setStateManagementNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<LocalStateExample> { LocalStateScreen(::onBack) }
    composable<SaveableStateExample> { SaveableStateScreen(::onBack) }
    composable<ViewModelLiveExample> { ViewModelLiveScreen(::onBack) }
    composable<FlowCollectionExample> { FlowCollectionScreen(::onBack) }
    composable<SideEffectsExample> { SideEffectsScreen(::onBack) }
    composable<BackHandlerExample> { BackHandlerScreen(::onBack) }
    composable<StateHoistingExample> { StateHoistingScreen(::onBack) }
}

private fun NavGraphBuilder.setGeneralNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<SimpleTextExample> { SimpleTextScreen(::onBack) }
    composable<ClickableExample> { ClickableScreen(::onBack) }
    composable<LayoutModifiersExample> { LayoutModifiersScreen(::onBack) }
    composable<BackgroundColorExample> { BackgroundColorScreen(::onBack) }
    composable<BoxStackingExample> { BoxStackingScreen(::onBack) }
    composable<AnimationBasicsExample> { AnimationBasicsScreen(::onBack) }
    composable<ThemeModeExample> { ThemeModeScreen(::onBack) }
    composable<EdgeToEdgeExample> { EdgeToEdgeScreen(::onBack) }
}