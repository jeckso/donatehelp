package com.jeckso.donatehelp.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.jeckso.donatehelp.ui.root.RootViewModel
import com.jeckso.donatehelp.ui.theme.DonateHelpTheme
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@AndroidEntryPoint
class MainActivityV2 : ComponentActivity() {
    @VisibleForTesting
    internal val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.e("ONCREATE ACTIVITY")
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
                DonateHelpTheme {
                    DonateHelpMainScreen()
                }
            }
        }
    }
}