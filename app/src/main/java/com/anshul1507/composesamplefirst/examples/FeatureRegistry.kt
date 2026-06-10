package com.anshul1507.composesamplefirst.examples

import com.anshul1507.composesamplefirst.examples.navigation.ScreenRoute


enum class FeatureCategory(val displayName: String) {
    GENERAL("General Basics"),
    STATE_MANAGEMENT("State Management"),
    MATERIAL_DESIGN("Material Design Components")
}
data class FeatureExample(
    val title: String,
    val description: String,
    val category: FeatureCategory,
    val destination: ScreenRoute
)

object FeatureRegistry {
    val generalList = listOf(
        FeatureExample(
            title = "Simple Text",
            description = "How do I display static and styled text on the screen?",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.SimpleTextExample
        ),
        FeatureExample(
            title = "Clickable Views",
            description = "How do I make any view clickable, toggle states, and customize ripple interactions?",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.ClickableExample
        ),
    )
    val stateManagementList = listOf(
        FeatureExample(
            title = "Process Death Retention",
            description = "How do I retain state across process death and configuration changes?",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.ProcessDeathExample
        )
    )
    val items = generalList + stateManagementList
}