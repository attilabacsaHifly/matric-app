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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appic.matricapp.R
import com.appic.matricapp.ui.screens.countyvignettes.CountyVignettesScreen
import com.appic.matricapp.ui.screens.initial.InitialScreen
import com.appic.matricapp.ui.screens.purchaseconfirmation.PurchaseConfirmationScreen
import com.appic.matricapp.ui.screens.purchasesuccess.PurchaseSuccessScreen
import com.appic.matricapp.ui.theme.MatricappNavy

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val canNavigateUp =
        navBackStackEntry?.destination?.route != NavigationDestination.INITIAL.destination
    val displayMainNavBar =
        navBackStackEntry?.destination?.route != NavigationDestination.PURCHASE_SUCCESS.destination

    Scaffold(
        topBar = {
            if (displayMainNavBar) {
                MainNavBar(canNavigateUp) { navController.navigateUp() }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.INITIAL.destination,
            modifier = Modifier.padding(padding)
        ) {
            addInitialScreen(
                onSelectCountyVignettes = {
                    navController.navigate(NavigationDestination.COUNTY_VIGNETTES.destination)
                },
                onConfirmPurchase = {
                    navController.navigate(NavigationDestination.PURCHASE_CONFIRMATION.destination)
                }
            )

            addCountyVignettesScreen {
                navController.navigate(NavigationDestination.PURCHASE_CONFIRMATION.destination)
            }

            addPurchaseConfirmationScreen(
                onSuccessfulPurchase = {
                    navController.navigate(NavigationDestination.PURCHASE_SUCCESS.destination) {
                        popUpTo(NavigationDestination.INITIAL.destination) {
                            inclusive = true
                        }
                    }
                },
                onCancelPurchase = {
                    navController.navigateUp()
                }
            )

            addPurchaseSuccessScreen {
                navController.navigate(NavigationDestination.INITIAL.destination) {
                    popUpTo(NavigationDestination.PURCHASE_SUCCESS.destination) { inclusive = true }
                }
            }
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
    onConfirmPurchase: () -> Unit
) {
    composable(NavigationDestination.INITIAL.destination) {
        InitialScreen(onSelectCountyVignettes, onConfirmPurchase)
    }
}

private fun NavGraphBuilder.addCountyVignettesScreen(onConfirmPurchase: () -> Unit) {
    composable(NavigationDestination.COUNTY_VIGNETTES.destination) {
        CountyVignettesScreen(onConfirmPurchase)
    }
}

private fun NavGraphBuilder.addPurchaseConfirmationScreen(
    onSuccessfulPurchase: () -> Unit,
    onCancelPurchase: () -> Unit
) {
    composable(NavigationDestination.PURCHASE_CONFIRMATION.destination) {
        PurchaseConfirmationScreen(onSuccessfulPurchase, onCancelPurchase)
    }
}

private fun NavGraphBuilder.addPurchaseSuccessScreen(onClose: () -> Unit) {
    composable(NavigationDestination.PURCHASE_SUCCESS.destination) {
        PurchaseSuccessScreen(onClose)
    }
}
