# Jetpack Compose Navigation Setup Guide

A comprehensive guide for beginners on how to add and configure navigation dependencies in an Android Jetpack Compose project.

## 📋 Table of Contents
- [Prerequisites](#prerequisites)
- [Step 1: Add Navigation Dependencies](#step-1-add-navigation-dependencies)
- [Step 2: Configure Material Icons (Optional)](#step-2-configure-material-icons-optional)
- [Step 3: Sync Project](#step-3-sync-project)
- [Step 4: Basic Navigation Setup](#step-4-basic-navigation-setup)
- [Project Structure](#project-structure)
- [Common Issues](#common-issues)

## 🎯 Prerequisites

Before you begin, make sure you have:
- Android Studio (Arctic Fox or newer recommended)
- Kotlin plugin installed
- Basic understanding of Jetpack Compose
- Minimum SDK 24 (Android 7.0)

## 📦 Step 1: Add Navigation Dependencies

### Option A: Using Version Catalog (Recommended)

This project uses the Gradle Version Catalog approach for managing dependencies.

#### 1.1 Update `gradle/libs.versions.toml`

Add the navigation versions and libraries to your version catalog file:

```toml
[versions]
navigationRuntimeKtx = "2.9.6"
navigationComposeAndroid = "2.9.5"

[libraries]
androidx-navigation-runtime-ktx = { group = "androidx.navigation", name = "navigation-runtime-ktx", version.ref = "navigationRuntimeKtx" }
androidx-navigation-compose-android = { group = "androidx.navigation", name = "navigation-compose-android", version.ref = "navigationComposeAndroid" }
```

#### 1.2 Update `app/build.gradle.kts`

Add the navigation dependencies to your app module's build file:

```kotlin
dependencies {
    // ... other dependencies
    
    // Navigation dependencies
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose.android)
    
    // ... other dependencies
}
```

### Option B: Direct Dependencies (Alternative)

If you prefer not to use version catalog, add directly to `app/build.gradle.kts`:

```kotlin
dependencies {
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.9.5")
    implementation("androidx.navigation:navigation-runtime-ktx:2.9.6")
}
```

## 🎨 Step 2: Configure Material Icons (Optional)

Material Icons are useful for bottom navigation and other UI elements:

Add to your `app/build.gradle.kts`:

```kotlin
dependencies {
    // Material Icons
    implementation("androidx.compose.material:material-icons-core:1.7.8")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
}
```

**Note:** 
- `material-icons-core` includes basic icons
- `material-icons-extended` includes additional icons (larger download size)

## 🔄 Step 3: Sync Project

After adding dependencies:

1. Click **"Sync Now"** banner at the top of the file
2. Or go to: **File → Sync Project with Gradle Files**
3. Wait for the sync to complete successfully

### Verify Sync
Check the **Build** tab at the bottom for any errors. A successful sync will show:
```
BUILD SUCCESSFUL in Xs
```

## 🚀 Step 4: Basic Navigation Setup

Once dependencies are added, you can start implementing navigation:

### 4.1 Create Routes File

Create `navigation/Routes.kt`:

```kotlin
package com.example.nav.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Settings : Routes("settings")
}
```

### 4.2 Create NavHost

Create `navigation/Navgraph.kt`:

```kotlin
package com.example.nav.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navgraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier
    ) {
        composable(Routes.Home.route) {
            // Your Home Screen
        }
        
        composable(Routes.Profile.route) {
            // Your Profile Screen
        }
        
        composable(Routes.Settings.route) {
            // Your Settings Screen
        }
    }
}
```

### 4.3 Setup in MainActivity

Update your `MainActivity.kt`:

```kotlin
package com.example.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.nav.navigation.Navgraph
import com.example.nav.ui.theme.YourAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            
            YourAppTheme {
                Navgraph(navController = navController)
            }
        }
    }
}
```

### 4.4 Navigate Between Screens

In your composables, use the navController to navigate:

```kotlin
// Navigate to a screen
navController.navigate(Routes.Profile.route)

// Navigate back
navController.navigateUp()
// or
navController.popBackStack()

// Navigate with arguments
navController.navigate("profile/${userId}")
```

## 📁 Project Structure

```
app/
├── src/
│   └── main/
│       └── java/
│           └── com/example/nav/
│               ├── MainActivity.kt
│               ├── navigation/
│               │   ├── Routes.kt
│               │   ├── Navgraph.kt
│               │   └── BottomNav.kt (optional)
│               └── screens/
│                   ├── HomeScreen.kt
│                   ├── ProfileScreen.kt
│                   └── SettingsScreen.kt
└── build.gradle.kts
```

## ⚠️ Common Issues

### Issue 1: "Unresolved reference: navigation"
**Solution:** Make sure you've synced your Gradle files after adding dependencies.

### Issue 2: Version Compatibility
**Solution:** Ensure all Compose dependencies use compatible versions. Check [Compose to Kotlin Compatibility Map](https://developer.android.com/jetpack/androidx/releases/compose-kotlin).

### Issue 3: Build fails with "Duplicate class"
**Solution:** Clean and rebuild:
```bash
./gradlew clean
./gradlew build
```

### Issue 4: Navigation not working
**Solution:** 
- Verify `rememberNavController()` is called in your composable
- Check that routes are defined correctly
- Ensure `NavHost` has proper `startDestination`

## 📚 Additional Resources

- [Official Navigation Documentation](https://developer.android.com/jetpack/compose/navigation)
- [Navigation Codelab](https://developer.android.com/codelabs/jetpack-compose-navigation)
- [Compose Navigation Samples](https://github.com/android/compose-samples)

## 🎓 Learning Path

1. ✅ Add dependencies (You are here!)
2. Create basic routes
3. Setup NavHost
4. Implement screen navigation
5. Add bottom navigation bar
6. Pass arguments between screens
7. Handle deep links
8. Add nested navigation

## 📝 Version Information

This project uses:
- **Navigation Compose:** 2.9.5
- **Navigation Runtime KTX:** 2.9.6
- **Kotlin:** 2.0.21
- **Compose BOM:** 2024.09.00
- **Min SDK:** 24
- **Target SDK:** 36

## 🤝 Contributing

Feel free to submit issues or pull requests to improve this guide!

## 📄 License

This project is for educational purposes.

---

**Happy Coding! 🚀**

For questions or issues, refer to the [official Android documentation](https://developer.android.com/jetpack/compose/navigation).
