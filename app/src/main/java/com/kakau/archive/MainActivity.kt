package com.kakau.archive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.kakau.archive.ui.GameViewModel
import com.kakau.archive.ui.KakauApp
import com.kakau.archive.ui.theme.KakauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: GameViewModel = viewModel()
            val state = vm.state.collectAsState().value
            KakauTheme(dark = state.darkTheme) { KakauApp(vm) }
        }
    }
}
