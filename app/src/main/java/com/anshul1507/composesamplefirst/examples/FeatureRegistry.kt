package com.anshul1507.composesamplefirst.examples


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
    val items = listOf(
        FeatureExample(
            title = "Simple Text",
            description = "How do I display static and styled text on the screen?",
            category = FeatureCategory.GENERAL,
            destination = ScreenRoute.SimpleTextExample
        ),
        FeatureExample(
            title = "Process Death Retention",
            description = "How do I retain state across process death and configuration changes?",
            category = FeatureCategory.STATE_MANAGEMENT,
            destination = ScreenRoute.ProcessDeathExample
        )
    )
}