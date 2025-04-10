package com.appic.matricapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.appic.matricapp.R
import com.appic.matricapp.ui.screens.countyvignettes.CountyVignettesScreen
import com.appic.matricapp.ui.screens.initial.InitialScreen
import com.appic.matricapp.ui.screens.models.Vignette
import com.appic.matricapp.ui.screens.purchaseconfirmation.PurchaseConfirmationScreen
import com.appic.matricapp.ui.screens.purchasesuccess.PurchaseSuccessScreen
import com.appic.matricapp.ui.theme.MatricappNavy
import kotlin.reflect.typeOf

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateUp =
        navBackStackEntry?.destination?.hasRoute(NavigationDestination.Initial::class) == false

    Scaffold(topBar = { MainNavBar(canNavigateUp) { navController.navigateUp() } }) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.Initial,
            modifier = Modifier.padding(padding)
        ) {
            addInitialScreen(
                onSelectCountyVignettes = {
                    navController.navigate(NavigationDestination.CountyVignettes)
                },
                onPurchaseSuccess = {
                    navController.navigate(NavigationDestination.PurchaseSuccess)
                }
            )

            addCountyVignettesScreen()

            addPurchaseConfirmationScreen()

            addPurchaseSuccessScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainNavBar(canNavigateUp: Boolean, onNavigateUp: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_title), style = typography.titleSmall) },
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = dimensionResource(R.dimen.dp_20),
                bottomEnd = dimensionResource(R.dimen.dp_20)
            )
        ),
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = MatricappNavy
                    )
                }
            }
        }
    )
}

private fun NavGraphBuilder.addInitialScreen(
    onSelectCountyVignettes: () -> Unit,
    onPurchaseSuccess: () -> Unit
) {
    composable<NavigationDestination.Initial> {
        InitialScreen(onSelectCountyVignettes, onPurchaseSuccess)
    }
}

private fun NavGraphBuilder.addCountyVignettesScreen() {
    composable<NavigationDestination.CountyVignettes> {
        CountyVignettesScreen()
    }
}

private fun NavGraphBuilder.addPurchaseConfirmationScreen() {
    val navTypeMap = mapOf(
        typeOf<String>() to NavType.StringType,
        typeOf<List<Vignette>>() to CustomNavType.vignettesType
    )
    composable<NavigationDestination.PurchaseConfirmation>(typeMap = navTypeMap) {
        val route = it.toRoute<NavigationDestination.PurchaseConfirmation>()
        PurchaseConfirmationScreen(route.vehiclePlate, route.vignettes)
    }
}

private fun NavGraphBuilder.addPurchaseSuccessScreen() {
    composable<NavigationDestination.PurchaseSuccess> {
        PurchaseSuccessScreen()
    }
}
