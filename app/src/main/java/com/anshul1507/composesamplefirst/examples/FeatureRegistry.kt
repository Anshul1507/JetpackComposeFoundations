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
        FeatureExample(
            title = "Layout Modifiers",
            description = "Mastering layout control: Padding, Margins, Offsets, and Fixed Aspect Ratios.",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.LayoutModifiersExample
        ),
        FeatureExample(
            title = "Background Colors & Gradients",
            description = "How do I apply solid colors, linear gradients, and elevation-aware Surface colors?",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.BackgroundColorExample
        ),
        FeatureExample(
            title = "FrameLayout Stacking (Box)",
            description = "How do I layer views on top of each other and anchor child items using custom alignments?",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.BoxStackingExample
        ),
        FeatureExample(
            title = "Rotation & Color Animations",
            description = "How do I implement infinite rotation loops and smooth value-driven color transitions?",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.AnimationBasicsExample
        )
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