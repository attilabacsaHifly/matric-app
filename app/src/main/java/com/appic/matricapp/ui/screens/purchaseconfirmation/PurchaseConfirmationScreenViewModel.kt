package com.appic.matricapp.ui.screens.purchaseconfirmation

import androidx.lifecycle.ViewModel
import com.appic.matricapp.common.Cache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchaseConfirmationScreenViewModel @Inject constructor(
    private val cache: Cache
) : ViewModel()
