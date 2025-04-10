package com.appic.matricapp.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.appic.matricapp.ui.screens.models.Vignette
import kotlinx.serialization.json.Json

object CustomNavType {

    val vignettesType = object : NavType<List<Vignette>>(false) {

        override fun get(bundle: Bundle, key: String): List<Vignette>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): List<Vignette> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<Vignette>): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: List<Vignette>) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
