package com.appic.matricapp.ui

import com.appic.matricapp.common.DataCache
import com.appic.matricapp.interactor.HighwayVignetteInteractor
import com.appic.matricapp.network.models.Category
import com.appic.matricapp.network.models.VignetteCategory
import com.appic.matricapp.network.models.VignetteType
import com.appic.matricapp.ui.screens.initial.InitialScreenState
import com.appic.matricapp.ui.screens.initial.InitialScreenViewModel
import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette
import com.appic.matricapp.utils.TestDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

// Unfortunately due to lack of time the test coverage is not complete.
@OptIn(ExperimentalCoroutinesApi::class)
class InitialScreenViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private val interactor: HighwayVignetteInteractor = mockk()
    private val dataCache: DataCache = mockk(relaxed = true)

    private lateinit var viewModel: InitialScreenViewModel

    @Test
    fun `when view model is created and infos returned then screen state is Success`() = runTest {
        val info = Info(vignettes = listOf(), counties = listOf())
        val vehicleInfo = VehicleInfo(
            vehicleOwnerName = "Tisha Carson",
            vehiclePlate = "ABC - 123"
        )

        coEvery { interactor.getInfo() } returns info
        coEvery { interactor.getVehicleInfo() } returns vehicleInfo

        createViewModel()
        advanceUntilIdle()

        assertTrue(viewModel.screenState.value is InitialScreenState.Loaded)

        verify { dataCache.cacheInfo(any()) }
        verify { dataCache.cacheVehicleInfo(any()) }
    }

    @Test
    fun `when view model is created and infos are null then screen state is Error`() = runTest {
        coEvery { interactor.getInfo() } returns null
        coEvery { interactor.getVehicleInfo() } returns null

        createViewModel()
        advanceUntilIdle()

        assertTrue(viewModel.screenState.value is InitialScreenState.Error)

        verify(exactly = 0) { dataCache.cacheInfo(any()) }
        verify(exactly = 0) { dataCache.cacheVehicleInfo(any()) }
    }

    @Test
    fun `when onConfirmPurchase is called then selectedNameVignettePair is added to data cache`() =
        runTest {
            val info = Info(
                vignettes = listOf(
                    Vignette(
                        category = Category.CAR,
                        vignetteCategory = VignetteCategory.D1,
                        vignetteTypes = listOf(VignetteType.DAY),
                        cost = 4500.5,
                        trxFee = 6.7
                    )
                ),
                counties = listOf()
            )
            val vehicleInfo = VehicleInfo(
                vehicleOwnerName = "Tisha Carson",
                vehiclePlate = "ABC - 123"
            )
            val selectedNameVignettePair = Pair("D1 - havi", info.vignettes.first())

            coEvery { interactor.getInfo() } returns info
            coEvery { interactor.getVehicleInfo() } returns vehicleInfo

            createViewModel()
            advanceUntilIdle()

            viewModel.onNameVignettePairSelected(selectedNameVignettePair)
            viewModel.onConfirmPurchase()

            verify { dataCache.addNameVignettePairToSelected(selectedNameVignettePair) }
        }

    private fun createViewModel() {
        viewModel = InitialScreenViewModel(testDispatcherRule.testDispatcher, interactor, dataCache)
    }
}
