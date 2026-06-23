package com.anshul1507.composesamplefirst.practice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anshul1507.composesamplefirst.practice.navigation.ScreenRoute.*
import com.anshul1507.composesamplefirst.practice.ui.screens.DashboardScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.BottomNavScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.ButtonStylingScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.CheckboxScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.CustomRippleScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.CustomShadowScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.DialogsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.FabScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.FilterChipsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.MaterialComponentsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.NavDrawerScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.RadioButtonScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.SliderScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.components.SnackbarActionScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.AnimationBasicsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.BackgroundColorScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.BoxStackingScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.ClickableScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.EdgeToEdgeScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.LayoutModifiersScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.SimpleTextScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.general.ThemeModeScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.layouts.AdaptiveConstraintsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.layouts.ConstraintLayoutScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.layouts.FlexboxLayoutScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.layouts.IntrinsicLayoutScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.lists.InfiniteScrollScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.lists.LazyColumnScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.lists.LazyGridScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.lists.LazyRowScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.lists.ListAnimationScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.lists.ScrollInterceptionScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.lists.StickyHeaderScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.media.ImageCachingScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.media.ImageLoaderScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.media.NetworkImageScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.media.StaticImageScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.media.VideoPlaybackScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.BackHandlerScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.FlowCollectionScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.LocalStateScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.SaveableStateScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.SideEffectsScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.StateHoistingScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement.ViewModelLiveScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText.AnimatedTextInlineScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText.SearchBarScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText.TextFieldCustomScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText.TypographyAdvancedScreen
import com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText.VisualTransformationScreen


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
        setTypographyNavGraphs(myNavController)
        setVirtualizationNavGraphs(myNavController)
        setMediaNavGraphs(myNavController)
        setAdvancedLayoutsNavGraphs(myNavController)
    }
}

private fun NavGraphBuilder.setAdvancedLayoutsNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<FlexboxLayoutsExample> { FlexboxLayoutScreen(::onBack) }
    composable<ConstraintLayoutExample> { ConstraintLayoutScreen(::onBack) }
    composable<AdaptiveConstraintsExample> { AdaptiveConstraintsScreen(::onBack) }
    composable<IntrinsicLayoutsExample> { IntrinsicLayoutScreen(::onBack) }

}

private fun NavGraphBuilder.setMediaNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<StaticImageExample> { StaticImageScreen(::onBack) }
    composable<NetworkImageExample> { NetworkImageScreen(::onBack) }
    composable<ImageLoaderExample> { ImageLoaderScreen(::onBack) }
    composable<ImageCachingExample> { ImageCachingScreen(::onBack) }
    composable<VideoPlaybackExample> { VideoPlaybackScreen(::onBack) }
}

private fun NavGraphBuilder.setVirtualizationNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<LazyColumnExample> { LazyColumnScreen(::onBack) }
    composable<LazyRowExample> { LazyRowScreen(::onBack) }
    composable<LazyGridExample> { LazyGridScreen(::onBack) }
    composable<StickyHeaderExample> { StickyHeaderScreen(::onBack) }
    composable<ScrollInterceptionExample> { ScrollInterceptionScreen(::onBack) }
    composable<ListAnimationExample> { ListAnimationScreen(::onBack) }
    composable<InfiniteScrollExample> { InfiniteScrollScreen(::onBack) }
}

private fun NavGraphBuilder.setTypographyNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<TypographyAdvancedExample> { TypographyAdvancedScreen(::onBack) }
    composable<TextFieldCustomExample> { TextFieldCustomScreen(::onBack) }
    composable<SearchBarExample> { SearchBarScreen(::onBack) }
    composable<VisualTransformationExample> { VisualTransformationScreen(::onBack) }
    composable<AnimatedTextInlineExample> { AnimatedTextInlineScreen(::onBack) }
}

private fun NavGraphBuilder.setComponentsNavGraphs(myNavController: NavHostController) {
    fun onBack() = myNavController.popBackStack()

    composable<MaterialComponentsExample> { MaterialComponentsScreen(::onBack) }
    composable<SnackbarActionExample> { SnackbarActionScreen(::onBack) }
    composable<SliderExample> { SliderScreen(::onBack) }
    composable<CheckboxExample> { CheckboxScreen(::onBack) }
    composable<RadioButtonExample> { RadioButtonScreen(::onBack) }
    composable<FABExample> { FabScreen(::onBack) }
    composable<BottomNavExample> { BottomNavScreen(::onBack) }
    composable<NavDrawerExample> { NavDrawerScreen(::onBack) }
    composable<CustomRippleExample> { CustomRippleScreen(::onBack) }
    composable<ButtonStylingExample> { ButtonStylingScreen(::onBack) }
    composable<DialogsExample> { DialogsScreen(::onBack) }
    composable<FilterChipsExample> { FilterChipsScreen(::onBack) }
    composable<CustomShadowExample> { CustomShadowScreen(::onBack) }
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