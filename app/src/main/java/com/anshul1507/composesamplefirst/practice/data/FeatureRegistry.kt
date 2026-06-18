package com.anshul1507.composesamplefirst.practice.data

import com.anshul1507.composesamplefirst.practice.navigation.ScreenRoute


enum class FeatureCategory(val displayName: String) {
    GENERAL("General Basics"),
    STATE_MANAGEMENT("State Management"),
    COMPONENTS("Material Design Components"),
    TYPOGRAPHY("Typography")
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
    val componentsList = getComponentsRoutes()
    val typographyList = getTypographyRoutes()

    val items = generalList + stateManagementList + componentsList
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
        ),
        FeatureExample(
            title = "StateFlow & Coroutine Flows",
            description = "Asynchronously collecting background streams in a lifecycle-aware manner using StateFlow.",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.FlowCollectionExample
        ),
        FeatureExample(
            title = "Side-Effects & LaunchedEffect",
            description = "How to safely launch coroutines, handle screen entry events, and manage side-effects.",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.SideEffectsExample
        ),
        FeatureExample(
            title = "System Back Interception",
            description = "How to intercept, halt, and custom handle hardware or gesture system back presses.",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.BackHandlerExample
        ),
        FeatureExample(
            title = "State Hoisting Patterns",
            description = "Transforming stateful elements into pure, testable, and reusable stateless composables.",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.StateHoistingExample
        )
    )
}

fun getComponentsRoutes(): List<FeatureExample> {
    return listOf(
        FeatureExample(
            title = "Cards & Progress Indicators",
            description = "Implementing standardized Material Design container cards and feedback indicators.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.MaterialComponentsExample
        ),
        FeatureExample(
            title = "Dynamic Snackbars with Actions",
            description = "Managing message queues, executing optional visual actions, and handling asynchronous snackbar feedback loops.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.SnackbarActionExample
        ),
        FeatureExample(
            title = "Continuous & Discrete Sliders",
            description = "Mastering quantitative user selection via smooth fluid sliders and snapped discrete intervals.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.SliderExample
        ),
        FeatureExample(
            title = "Multi-State Checkboxes",
            description = "Mastering binary and Tri-State checkbox hierarchies for bulk selection architectures.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.CheckboxExample
        ),
        FeatureExample(
            title = "Radio Button Groups",
            description = "Implementing mutually exclusive selection patterns using state-driven groups.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.RadioButtonExample
        ),
        FeatureExample(
            title = "Anchored Floating Action Buttons",
            description = "Implementing primary screen actions using scaffold-anchored FABs and extended FAB variants.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.FABExample
        ),
        FeatureExample(
            title = "Bottom Navigation Bars",
            description = "Building functional, state-driven bottom navigation layouts using Material 3 slots.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.BottomNavExample
        ),
        FeatureExample(
            title = "Navigation Drawers",
            description = "Implementing state-driven Modal and Dismissible navigation drawer structures.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.NavDrawerExample
        ),
        FeatureExample(
            title = "Custom Ripple Effects",
            description = "Customizing and overriding touch interaction feedback, ripple colors, and indication scopes.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.CustomRippleExample
        ),
        FeatureExample(
            title = "Button Styling & Typography",
            description = "Mastering Standard, Filled, and Tonal buttons with custom colors, shapes, and integrated loaders.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.ButtonStylingExample
        ),
        FeatureExample(
            title = "Alert Dialogs & Custom Modals",
            description = "Mastering standard Material 3 confirmations and fully tailored, custom layout modal dialogs.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.DialogsExample
        ),
        FeatureExample(
            title = "Wrapping Filter Chips",
            description = "Building multi-row wrapping tag layouts using Material 3 Filter Chips and ContextualFlowRow.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.FilterChipsExample
        ),
        FeatureExample(
            title = "Custom Shadows & Elevation",
            description = "Mastering Material 3 tonal elevations and constructing custom drop-shadow rendering modifiers.",
            category = FeatureCategory.COMPONENTS,
            destination = ScreenRoute.CustomShadowExample
        )
    )
}

fun getTypographyRoutes(): List<FeatureExample> {
    return listOf(
        FeatureExample(
            title = "Advanced Typography & Fonts",
            description = "Mastering custom font families, inline text spans, and mixed weights within a single text element.",
            category = FeatureCategory.TYPOGRAPHY,
            destination = ScreenRoute.TypographyAdvancedExample
        ),
        FeatureExample(
            title = "Custom TextFields & Inputs",
            description = "Mastering state-controlled input fields, error validations, custom styling, and numeric keyboards.",
            category = FeatureCategory.TYPOGRAPHY,
            destination = ScreenRoute.TextFieldCustomExample
        ),
        FeatureExample(
            title = "Search Bars & Filtering",
            description = "Implementing Material 3 SearchBars with dynamic query filtering and expandable results.",
            category = FeatureCategory.TYPOGRAPHY,
            destination = ScreenRoute.SearchBarExample
        ),
        FeatureExample(
            title = "Visual Transformations",
            description = "Implementing input masks for credit card grouping and secure password visibility toggles.",
            category = FeatureCategory.TYPOGRAPHY,
            destination = ScreenRoute.VisualTransformationExample
        )
    )
}