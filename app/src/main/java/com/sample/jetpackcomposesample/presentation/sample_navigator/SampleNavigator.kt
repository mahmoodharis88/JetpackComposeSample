package com.sample.jetpackcomposesample.presentation.sample_navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sample.jetpackcomposesample.R
import com.sample.jetpackcomposesample.presentation.components.AppButton
import com.sample.jetpackcomposesample.presentation.home.HomeScreen
import com.sample.jetpackcomposesample.presentation.home.HomeViewModel
import com.sample.jetpackcomposesample.presentation.navgraph.Route
import com.sample.jetpackcomposesample.presentation.sample_navigator.components.BottomNavigationItem
import com.sample.jetpackcomposesample.presentation.sample_navigator.components.SampleBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            SampleBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val state = viewModel.uiStateLiveData.collectAsState().value
                HomeScreen(state = state, event =  viewModel::onEvent)
            }
            composable(route = Route.SearchScreen.route) {
                AppButton(text = "Search Screen") {

                }
//                val viewModel: SearchViewModel = hiltViewModel()
//                val state = viewModel.state.value
//                OnBackClickStateSaver(navController = navController)
//                SearchScreen(
//                    state = state,
//                    event = viewModel::onEvent,
//                    navigateToDetails = { article ->
//                        navigateToDetails(
//                            navController = navController,
//                            article = article
//                        )
//                    }
//                )
            }
            composable(route = Route.DetailsScreen.route) {
                AppButton(text = "Details Screen") {

                }
//                val viewModel: DetailsViewModel = hiltViewModel()
//                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
//                    ?.let { article ->
//                        DetailsScreen(
//                            article = article,
//                            event = viewModel::onEvent,
//                            navigateUp = { navController.navigateUp() },
//                            sideEffect = viewModel.sideEffect
//                        )
//                    }

            }
            composable(route = Route.BookmarkScreen.route) {
                AppButton(text = "Bookmarks Screen") {

                }
//                val viewModel: BookmarkViewModel = hiltViewModel()
//                val state = viewModel.state.value
//                OnBackClickStateSaver(navController = navController)
//                BookmarkScreen(
//                    state = state,
//                    navigateToDetails = { article ->
//                        navigateToDetails(
//                            navController = navController,
//                            article = article
//                        )
//                    }
//                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}