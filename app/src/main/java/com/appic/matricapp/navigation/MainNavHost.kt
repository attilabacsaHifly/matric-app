package com.appic.matricapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appic.matricapp.ui.screens.countyvignettes.CountyVignettesScreen
import com.appic.matricapp.ui.screens.initial.InitialScreen
import com.appic.matricapp.ui.screens.purchaseconfirmation.PurchaseConfirmationScreen
import com.appic.matricapp.ui.screens.purchasesuccess.PurchaseSuccessScreen

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateUp = navBackStackEntry?.destination?.route != NavigationDestination.INITIAL.route

    Scaffold(topBar = { MainNavBar(canNavigateUp) { navController.navigateUp() } }) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.INITIAL.route,
            modifier = Modifier.padding(padding)
        ) {
            addInitialScreen(
                onSelectCountyVignettes = {
                    navController.navigate(NavigationDestination.COUNTY_VIGNETTES.route)
                },
                onConfirmPurchase = {
                    navController.navigate(NavigationDestination.PURCHASE_CONFIRMATION.route)
                })

            addCountyVignettesScreen()

            addPurchaseConfirmationScreen()

            addPurchaseSuccessScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainNavBar(canNavigateUp: Boolean, onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { Text("E-matrica") }, // TODO
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ),
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}

private fun NavGraphBuilder.addInitialScreen(
    onSelectCountyVignettes: () -> Unit,
    onConfirmPurchase: () -> Unit
) {
    composable(NavigationDestination.INITIAL.route) {
        InitialScreen(onSelectCountyVignettes, onConfirmPurchase)
    }
}

private fun NavGraphBuilder.addCountyVignettesScreen() {
    composable(NavigationDestination.COUNTY_VIGNETTES.route) {
        CountyVignettesScreen()
    }
}

private fun NavGraphBuilder.addPurchaseConfirmationScreen() {
    composable(NavigationDestination.PURCHASE_CONFIRMATION.route) {
        PurchaseConfirmationScreen()
    }
}

private fun NavGraphBuilder.addPurchaseSuccessScreen() {
    composable(NavigationDestination.PURCHASE_SUCCESS.route) {
        PurchaseSuccessScreen()
    }
}
