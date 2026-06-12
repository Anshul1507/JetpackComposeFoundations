package com.anshul1507.composesamplefirst.practice.navigation

import kotlinx.serialization.Serializable

sealed interface ScreenRoute {

    @Serializable
    data object Dashboard: ScreenRoute

    /**
     * GENERAL ROUTES
     */
    @Serializable
    data object SimpleTextExample: ScreenRoute
    @Serializable
    data object ClickableExample: ScreenRoute
    @Serializable
    data object LayoutModifiersExample: ScreenRoute
    @Serializable
    data object BackgroundColorExample: ScreenRoute
    @Serializable
    data object BoxStackingExample: ScreenRoute
    @Serializable
    data object AnimationBasicsExample: ScreenRoute
    @Serializable
    data object ThemeModeExample: ScreenRoute
    @Serializable
    data object EdgeToEdgeExample: ScreenRoute

    /**
     * STATE MANAGEMENT ROUTES
     */
    @Serializable
    data object LocalStateExample: ScreenRoute
    @Serializable
    data object SaveableStateExample: ScreenRoute
    @Serializable
    data object ViewModelLiveExample: ScreenRoute

    //As we add the features through out, just append those routes here
//    @Serializable
//    data object Example: ScreenRoute
}