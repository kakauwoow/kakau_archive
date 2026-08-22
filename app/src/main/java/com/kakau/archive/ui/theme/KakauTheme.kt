package com.kakau.archive.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val KakauOrange=Color(0xFFFF8A00)
private val DarkColors=darkColorScheme(primary=KakauOrange,secondary=Color(0xFFFFB14A),background=Color(0xFF0A0B0E),surface=Color(0xFF111319),surfaceVariant=Color(0xFF1A1D24))
private val LightColors=lightColorScheme(primary=Color(0xFFD96B00),secondary=Color(0xFF9A4C00),background=Color(0xFFF4F5F7),surface=Color.White,surfaceVariant=Color(0xFFE9EBEF))
@Composable fun KakauTheme(dark:Boolean, content:@Composable()->Unit){ MaterialTheme(colorScheme=if(dark) DarkColors else LightColors, typography=Typography(), content=content) }
