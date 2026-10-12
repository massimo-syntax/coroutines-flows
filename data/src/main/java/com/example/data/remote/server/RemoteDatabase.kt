package com.example.data.remote.server

import com.example.data.model.SomeNetworkDataDto
import com.example.domain.model.AndroidFeature
import kotlin.uuid.Uuid

val someNetworkDataDtoList: List<SomeNetworkDataDto> = List(50) { index ->
    SomeNetworkDataDto(
        id = Uuid.random().toString(),
        title = "Title ${index + 1}",
        body = "Body content for item ${index + 1}",
        dateTime = System.currentTimeMillis() - (index * 86_400_000L)
    )
}

val addSkillInstructions: List<SomeNetworkDataDto> = listOf(
    SomeNetworkDataDto(
        id = Uuid.random().toString(),
        title = "Skill Instruction 1",
        body = "Content for agent skill instruction 1",
        dateTime = System.currentTimeMillis()
    ),
    SomeNetworkDataDto(
        id = Uuid.random().toString(),
        title = "Skill Instruction 2",
        body = "Content for agent skill instruction 2",
        dateTime = System.currentTimeMillis()
    )
)

val androidFeaturesList: List<AndroidFeature> = listOf(
    AndroidFeature(
        id = "material-expressive",
        tag = "UI & Motion",
        title = "Material 3 Expressive System & Haptics",
        description = "Dynamic spring physics, squircle corner morphing, and tactile haptic feedback tokens integrated directly into Compose. Key APIs include animateItem(), expressive color schemes, and HapticFeedbackType."
    ),
    AndroidFeature(
        id = "private-space",
        tag = "Security & Privacy",
        title = "Private Space & Biometric Isolation",
        description = "A separate sandboxed environment for sensitive apps protected by biometric locks. Utilizes UserManager.isPrivateProfile() and ACTION_CREATE_PRIVATE_SPACE to isolate notifications, data, and background tasks."
    ),
    AndroidFeature(
        id = "edge-to-edge",
        tag = "Display & Layout",
        title = "Enforced Edge-to-Edge Windows",
        description = "Applications draw edge-to-edge behind system bars by default. Jetpack Compose seamlessly consumes WindowInsets.safeDrawing and windowInsetsPadding to render content safely without layout shifts."
    ),
    AndroidFeature(
        id = "predictive-back",
        tag = "Navigation & Gestures",
        title = "Predictive Back Gestures & Transitions",
        description = "System and cross-activity animations that dynamically preview destination screens as users swipe from the edge. Implemented using PredictiveBackHandler and OnBackAnimationCallback with progress tracking."
    ),
    AndroidFeature(
        id = "satellite-messaging",
        tag = "Connectivity",
        title = "Carrier Satellite Messaging & Emergency SMS",
        description = "Native OS support for non-terrestrial satellite networks. Applications use TelephonyManager.serviceState and network capabilities to detect satellite mode and prioritize essential communication off-grid."
    ),
    AndroidFeature(
        id = "low-light-boost",
        tag = "Camera & Media",
        title = "Low Light Boost & In-App Viewfinder Exposure",
        description = "Real-time viewfinder brightening in extreme low light conditions for Camera2 and CameraX, exposing CONTROL_AE_MODE_ON_LOW_LIGHT_BOOST_BRIGHTNESS_PRIORITY and granular flash controls."
    ),
    AndroidFeature(
        id = "16kb-page-sizes",
        tag = "Performance & Silicon",
        title = "16 KB Memory Page Size Support",
        description = "Kernel and runtime alignment for modern ARM chipsets using 16 KB virtual memory pages, delivering 5% to 10% faster application startup and reduced memory fragmentation across native libraries."
    ),
    AndroidFeature(
        id = "app-archiving",
        tag = "Storage Management",
        title = "System Application Archiving",
        description = "Operating system hibernates rarely used apps, freeing up to 95% of storage space while preserving user credentials and app data. Powered by PackageInstaller.requestArchive() and requestUnarchive()."
    ),
    AndroidFeature(
        id = "notification-cooldown",
        tag = "System Experience",
        title = "Adaptive Notification Sound Cooldown",
        description = "Softens alerting sound and haptic volume automatically when rapid notifications arrive in quick succession from the same application, avoiding user alert fatigue while maintaining status bar icons."
    ),
    AndroidFeature(
        id = "partial-screen-sharing",
        tag = "Media & Privacy",
        title = "Partial Screen Window Capture",
        description = "Allows users to share or record an individual application window instead of the entire device screen, utilizing MediaProjectionConfig.createConfigForUserChoice() to safeguard background apps and notifications."
    )
)
