package com.example.runerotouch

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runerotouch.ui.screens.catalog.CatalogScreen
import com.example.runerotouch.ui.screens.settings.SettingsScreen
import com.example.runerotouch.ui.theme.RUNEROTouchTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val activity = this as Activity
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            RUNEROTouchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
                hideNavigationBar(activity = this)
            }
        }
    }
}

@SuppressLint("InlinedApi")
@Composable
fun hideNavigationBar(activity: Activity) {
    DisposableEffect(Unit) {
        val flags = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        activity.window.decorView.systemUiVisibility = flags

        onDispose {
            // Сброс настроек при уничтожении эффекта
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Catalog.route) {
        composable(Screen.Catalog.route) {
            CatalogScreen(
                onClickSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onClickCatalog = { navController.navigate(Screen.Catalog.route) }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object Catalog : Screen("catalog")
    data object Settings : Screen("settings")
}