package com.anshul1507.composesamplefirst.practice.data

import com.anshul1507.composesamplefirst.practice.navigation.ScreenRoute


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
    val generalList = getGeneralRoutes()
    val stateManagementList = getStateManagementRoutes()
    val items = generalList + stateManagementList
}

fun getGeneralRoutes(): List<FeatureExample> {
    return listOf(
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
        ),
        FeatureExample(
            title = "Theme & Dark Mode Styling",
            description = "How do styles and themes work? Accessing dynamic system configurations and handling Dark Mode.",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.ThemeModeExample
        ),
        FeatureExample(
            title = "Edge-to-Edge & Window Insets",
            description = "How do I safely draw UI content underneath the system status bar and navigation bar?",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.EdgeToEdgeExample
        )
    )
}

fun getStateManagementRoutes(): List<FeatureExample> {
    return listOf(
        FeatureExample(
            title = "Local State Mutation",
            description = "Mastering local state tracking using remember, mutableStateOf, and continuous UI recomposition.",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.LocalStateExample
        ),
        FeatureExample(
            title = "Configuration & Process Death Survival",
            description = "How do I retain input states across screen rotations and background OS termination using rememberSaveable?",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.SaveableStateExample
        ),
        FeatureExample(
            title = "ViewModel & LiveData Integration",
            description = "How do I decouple business logic into ViewModels and collect LiveData updates safely?",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.ViewModelLiveExample
        )
    )
}