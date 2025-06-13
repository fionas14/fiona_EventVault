package com.fionasiregar0032.eventvault.navigation

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
}