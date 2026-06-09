package com.anshul1507.composesamplefirst.examples

import kotlinx.serialization.Serializable

sealed interface ScreenRoute {

    @Serializable
    data object Dashboard: ScreenRoute

    @Serializable
    data object SimpleTextExample: ScreenRoute

    @Serializable
    data object ProcessDeathExample: ScreenRoute

    //As we add the features through out, just append those routes here
//    @Serializable
//    data object Example: ScreenRoute
}