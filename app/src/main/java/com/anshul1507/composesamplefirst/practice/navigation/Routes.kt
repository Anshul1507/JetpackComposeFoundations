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
    @Serializable
    data object FlowCollectionExample: ScreenRoute
    @Serializable
    data object SideEffectsExample: ScreenRoute
    @Serializable
    data object BackHandlerExample: ScreenRoute
    @Serializable
    data object StateHoistingExample: ScreenRoute

    /**
     * MATERIAL DESIGN COMPONENTS
     */
    @Serializable
    data object MaterialComponentsExample: ScreenRoute
    @Serializable
    data object SnackbarActionExample: ScreenRoute
    @Serializable
    data object SliderExample: ScreenRoute
    @Serializable
    data object CheckboxExample: ScreenRoute
    @Serializable
    data object RadioButtonExample: ScreenRoute
    @Serializable
    data object FABExample: ScreenRoute
    @Serializable
    data object BottomNavExample: ScreenRoute
    @Serializable
    data object NavDrawerExample: ScreenRoute
    @Serializable
    data object CustomRippleExample: ScreenRoute
    @Serializable
    data object ButtonStylingExample: ScreenRoute
    @Serializable
    data object DialogsExample: ScreenRoute
    @Serializable
    data object FilterChipsExample: ScreenRoute
    @Serializable
    data object CustomShadowExample: ScreenRoute

    /**
     * TYPOGRAPHY AND ADVANCED TEXT
     */
    @Serializable
    data object TypographyAdvancedExample: ScreenRoute
    @Serializable
    data object TextFieldCustomExample: ScreenRoute
    @Serializable
    data object SearchBarExample: ScreenRoute
    @Serializable
    data object VisualTransformationExample: ScreenRoute
    @Serializable
    data object AnimatedTextInlineExample: ScreenRoute

    /**
     * VIRTUALIZATION ROUTES
     */
    @Serializable
    data object LazyColumnExample: ScreenRoute
    @Serializable
    data object LazyRowExample: ScreenRoute
    @Serializable
    data object LazyGridExample: ScreenRoute
    @Serializable
    data object StickyHeaderExample: ScreenRoute
    @Serializable
    data object ScrollInterceptionExample: ScreenRoute
    @Serializable
    data object ListAnimationExample: ScreenRoute
    @Serializable
    data object InfiniteScrollExample: ScreenRoute

    /**
     * MEDIA
     */
    @Serializable
    data object StaticImageExample: ScreenRoute
    @Serializable
    data object NetworkImageExample: ScreenRoute
    @Serializable
    data object ImageLoaderExample: ScreenRoute
    @Serializable
    data object ImageCachingExample: ScreenRoute
    @Serializable
    data object VideoPlaybackExample: ScreenRoute

    /**
     * ADVANCED LAYOUTS
     */
    @Serializable
    data object FlexboxLayoutsExample: ScreenRoute
    @Serializable
    data object ConstraintLayoutExample: ScreenRoute
    @Serializable
    data object AdaptiveConstraintsExample: ScreenRoute
    @Serializable
    data object IntrinsicLayoutsExample: ScreenRoute

    /**
     * CUSTOM DRAWING
     */
    @Serializable
    data object CanvasDrawingExample: ScreenRoute
    @Serializable
    data object MeasuringScaleExample: ScreenRoute
    @Serializable
    data object ZoomPanExample: ScreenRoute
    @Serializable
    data object PaintCanvasExample: ScreenRoute


    //As we add the features through out, just append those routes here
//    @Serializable
//    data object Example: ScreenRoute
}