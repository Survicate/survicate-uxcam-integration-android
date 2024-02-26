# Survicate - UXCam integration for Android
A thin library designed for seamless integration between [Survicate](https://survicate.com/) and [UXCam](https://uxcam.com/) on Android. It automatically sends survey answers coming from Survicate SDK as UXCam events that can be previewed directly in the UXCam panel.

## Adding the dependency

Make sure you have defined the Maven repositories for Survicate and UxCam:
```
repositories {
    // ...
    maven { url 'https://repo.survicate.com' }
    maven { url 'https://sdk.uxcam.com/android/' }
}
```

Add the dependency to your app's `build.gradle` file:

```
dependencies {
    // The integration library
    implementation 'com.survicate:survicate-uxcam-integration:<latest_version>'
    
    // Survicate and UXCam libs you should already have
    implementation 'com.survicate:survicate-sdk:<latest_version>'
    implementation 'com.uxcam:uxcam:<latest_version>'
}
```

## Usage

> Note that for the plugin to function properly, it is essential that both the Survicate and UxCam SDKs have been initialized correctly as outlined in their respective documentation:
> - [Survicate developer docs](https://developers.survicate.com/mobile-sdk/android/)
> - [UXCam developer docs](https://developer.uxcam.com/docs/android)

In order to activate the integration library, go to the place in your app where you initialize the Survicate SDK and register `SurvicateUXCamIntegration` as an event listener:

```kotlin
Survicate.init(applicationContext)
Survicate.addEventListener(SurvicateUXCamIntegration())
```

That's it. You can use all UXCam and Survicate features as usual. Every survey answer will be automatically logged to the UXCam using the `UXCam.logEvent` method.
