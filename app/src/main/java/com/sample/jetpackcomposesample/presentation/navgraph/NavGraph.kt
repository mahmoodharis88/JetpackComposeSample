package com.sample.jetpackcomposesample.presentation.navgraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sample.jetpackcomposesample.presentation.onboarding.OnBoardingScreen
import com.sample.jetpackcomposesample.presentation.onboarding.OnBoardingViewModel
import com.sample.jetpackcomposesample.presentation.sample_navigator.SampleNavigator

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route, startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.SampleNavigation.route,
            startDestination = Route.SampleNavigatorScreen.route
        ) {
            composable(route = Route.SampleNavigatorScreen.route) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                        .statusBarsPadding()
                ) {
                    SampleNavigator()
                }

            }
        }
    }
}