package com.jeruk.artistexplorerapp.ui.route


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeruk.artistexplorerapp.ui.view.ErrorPage
import com.jeruk.artistexplorerapp.ui.view.HomePage
import com.jeruk.artistexplorerapp.ui.view.LoadingPage
import com.jeruk.artistexplorerapp.ui.viewmodel.ArtistArtistViewModel

enum class AppView(
    val title: String,
    val icon: ImageVector? = null
) {
    Home("Home"),
    Album("Album"),
    Loading("Loading"),
    Error("Error")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppRoute() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val currentView = AppView.entries.find { it.name == currentRoute }

    Scaffold(
        topBar = {
            MyTopAppBar(
                currentView = currentView,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }

    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppView.Home.name
        ) {
            composable(route = AppView.Home.name) {
                HomePage(navController = navController)
            }

//            composable(route = AppView.MovieDetail.name + "/{title}") { backStackEntry ->
//                MovieDetailView(
//                    title = backStackEntry.arguments?.getString("title")!!
//                )
//            }

            composable(route = AppView.Loading.name) {
                LoadingPage()
            }

            composable(route = AppView.Error.name) {
                ErrorPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentView: AppView?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArtistArtistViewModel = viewModel()
) {
    val artist by viewModel.artist.collectAsState()
    CenterAlignedTopAppBar(
        title = {
            Text(artist.nameArtist)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1C2021),
            titleContentColor = Color(0xFFB5B5B3)
        ),
        modifier = modifier,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "Back"
//                    )
//                }
//            }
//        }
    )
}
