package com.appic.matricapp.network.api

import com.appic.matricapp.network.models.GetInfoResponse
import com.appic.matricapp.network.models.GetVehicleResponse
import com.appic.matricapp.network.models.OrderVignettesRequest
import com.appic.matricapp.network.models.OrderVignettesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HighwayVignetteAPI {

    @GET(ENDPOINT_GET_INFO)
    suspend fun getInfo(): Response<GetInfoResponse>

    @GET(ENDPOINT_GET_VEHICLE)
    suspend fun getVehicle(): Response<GetVehicleResponse>

    @POST(ENDPOINT_ORDER_VIGNETTES)
    suspend fun orderVignettes(
        @Body request: OrderVignettesRequest
    ): Response<OrderVignettesResponse>

    companion object {
        private const val PATH = "v1/highway"

        private const val ENDPOINT_GET_INFO = "$PATH/info"
        private const val ENDPOINT_GET_VEHICLE = "$PATH/vehicle"
        private const val ENDPOINT_ORDER_VIGNETTES = "$PATH/order"
    }
}
