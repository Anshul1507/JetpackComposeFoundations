package com.anshul1507.composesamplefirst.examples.navigation

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

    /**
     * STATE MANAGEMENT ROUTES
     */
    @Serializable
    data object ProcessDeathExample: ScreenRoute

    //As we add the features through out, just append those routes here
//    @Serializable
//    data object Example: ScreenRoute
}