/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shashank.pocketai.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.shashank.pocketai.proto.Theme

private val lightScheme =
  lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = clayCanvas,
    onBackground = clayTextPrimary,
    surface = clayCanvas,
    onSurface = clayTextPrimary,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
  )

private val darkScheme =
  darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = clayCanvasDark,
    onBackground = clayTextPrimaryDark,
    surface = clayCanvasDark,
    onSurface = clayTextPrimaryDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
  )

@Immutable
data class CustomColors(
  val appTitleGradientColors: List<Color> = listOf(),
  val tabHeaderBgColor: Color = Color.Transparent,
  val taskCardBgColor: Color = Color.Transparent,
  val taskBgColors: List<Color> = listOf(),
  val taskBgGradientColors: List<List<Color>> = listOf(),
  val taskIconColors: List<Color> = listOf(),
  val taskIconShapeBgColor: Color = Color.Transparent,
  val homeBottomGradient: List<Color> = listOf(),
  val userBubbleBgColor: Color = Color.Transparent,
  val agentBubbleBgColor: Color = Color.Transparent,
  val linkColor: Color = Color.Transparent,
  val successColor: Color = Color.Transparent,
  val recordButtonBgColor: Color = Color.Transparent,
  val waveFormBgColor: Color = Color.Transparent,
  val modelInfoIconColor: Color = Color.Transparent,
  val warningContainerColor: Color = Color.Transparent,
  val warningTextColor: Color = Color.Transparent,
  val errorContainerColor: Color = Color.Transparent,
  val errorTextColor: Color = Color.Transparent,
  val newFeatureContainerColor: Color = Color.Transparent,
  val newFeatureTextColor: Color = Color.Transparent,
  val bgStarColor: Color = Color.Transparent,
  val promoBannerBgBrush: Brush = Brush.verticalGradient(listOf(Color.Transparent)),
  val promoBannerIconBgBrush: Brush = Brush.verticalGradient(listOf(Color.Transparent)),
)

val LocalCustomColors = staticCompositionLocalOf { CustomColors() }

val lightCustomColors =
  CustomColors(
    appTitleGradientColors = listOf(Color(0xFFA78BFA), Color(0xFF7C3AED)),
    tabHeaderBgColor = Color(0xFF7C3AED),
    taskCardBgColor = Color(0xFFF4F1FA),
    taskBgColors =
      listOf(
        Color(0xFFFAF5FF),
        Color(0xFFFDF2F8),
        Color(0xFFF0F9FF),
        Color(0xFFFFFBEB),
      ),
    taskBgGradientColors =
      listOf(
        listOf(Color(0xFFA78BFA), Color(0xFF7C3AED)),
        listOf(Color(0xFFF472B6), Color(0xFFDB2777)),
        listOf(Color(0xFF38BDF8), Color(0xFF0EA5E9)),
        listOf(Color(0xFFFBBF24), Color(0xFFF59E0B)),
      ),
    taskIconColors =
      listOf(
        Color(0xFF7C3AED),
        Color(0xFFDB2777),
        Color(0xFF0EA5E9),
        Color(0xFFF59E0B),
      ),
    taskIconShapeBgColor = Color.White,
    homeBottomGradient = listOf(Color(0x00F4F1FA), Color(0xFFE8DEFF)),
    agentBubbleBgColor = Color(0xFFF0EAFA),
    userBubbleBgColor = Color(0xFF5B21B6),
    linkColor = Color(0xFF7C3AED),
    successColor = Color(0xFF10B981),
    recordButtonBgColor = Color(0xFFDB2777),
    waveFormBgColor = Color(0xFF635F69),
    modelInfoIconColor = Color(0xFFA78BFA),
    warningContainerColor = Color(0xFFFEF3C7),
    warningTextColor = Color(0xFFF59E0B),
    errorContainerColor = Color(0xFFFCE7F3),
    errorTextColor = Color(0xFFDB2777),
    newFeatureContainerColor = Color(0xFFEDE9FE),
    newFeatureTextColor = Color(0xFF7C3AED),
    bgStarColor = Color(0x3AA78BFA),
    promoBannerBgBrush =
      Brush.linearGradient(
        colorStops =
          arrayOf(
            0.0f to Color(0x427C3AED),
            0.6154f to Color(0x425B21B6),
            1.0f to Color(0x424C1D95),
          ),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY),
      ),
    promoBannerIconBgBrush =
      Brush.linearGradient(
        colorStops =
          arrayOf(
            0.2442f to Color(0x3B7C3AED),
            0.4296f to Color(0x3B0EA5E9),
            0.6651f to Color(0x3BA78BFA),
          ),
        start = Offset(0f, 1f),
        end = Offset(1f, 0f),
      ),
  )

val darkCustomColors =
  CustomColors(
    appTitleGradientColors = listOf(Color(0xFFBBA4F5), Color(0xFF9D5FF0)),
    tabHeaderBgColor = Color(0xFF9D5FF0),
    taskCardBgColor = Color(0xFF251D30),
    taskBgColors =
      listOf(
        Color(0xFF1E1729),
        Color(0xFF1F1524),
        Color(0xFF15202B),
        Color(0xFF1F1C15),
      ),
    taskBgGradientColors =
      listOf(
        listOf(Color(0xFFBBA4F5), Color(0xFF9D5FF0)),
        listOf(Color(0xFFF472B6), Color(0xFFE0528E)),
        listOf(Color(0xFF38BDF8), Color(0xFF38B8F2)),
        listOf(Color(0xFFFBBF24), Color(0xFFF9B234)),
      ),
    taskIconColors =
      listOf(
        Color(0xFF9D5FF0),
        Color(0xFFE0528E),
        Color(0xFF38B8F2),
        Color(0xFFF9B234),
      ),
    taskIconShapeBgColor = Color(0xFF251D30),
    homeBottomGradient = listOf(Color(0x001A1721), Color(0x1A9D5FF0)),
    agentBubbleBgColor = Color(0xFF251D30),
    userBubbleBgColor = Color(0xFF3B1F6E),
    linkColor = Color(0xFFBBA4F5),
    successColor = Color(0xFF34C999),
    recordButtonBgColor = Color(0xFFE0528E),
    waveFormBgColor = Color(0xFFA096B4),
    modelInfoIconColor = Color(0xFFBBA4F5),
    warningContainerColor = Color(0xFF3D3520),
    warningTextColor = Color(0xFFF9B234),
    errorContainerColor = Color(0xFF3D1F30),
    errorTextColor = Color(0xFFE0528E),
    newFeatureContainerColor = Color(0xFF2D1F4E),
    newFeatureTextColor = Color(0xFFBBA4F5),
    bgStarColor = Color(0x199D5FF0),
    promoBannerBgBrush =
      Brush.linearGradient(
        colorStops =
          arrayOf(
            0.0f to Color(0x823B1F6E),
            0.8077f to Color(0x821A1721),
          ),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY),
      ),
    promoBannerIconBgBrush =
      Brush.linearGradient(
        colorStops =
          arrayOf(
            0.2442f to Color(0x6F9D5FF0),
            0.4296f to Color(0x6F38B8F2),
            0.6651f to Color(0x6FBBA4F5),
          ),
        start = Offset(0f, 1f),
        end = Offset(1f, 0f),
      ),
  )

val MaterialTheme.customColors: CustomColors
  @Composable @ReadOnlyComposable get() = LocalCustomColors.current

/**
 * Controls the color of the phone's status bar icons based on whether the app is using a dark
 * theme.
 */
@Composable
fun StatusBarColorController(useDarkTheme: Boolean) {
  val view = LocalView.current
  val currentWindow = (view.context as? Activity)?.window

  if (currentWindow != null) {
    SideEffect {
      WindowCompat.setDecorFitsSystemWindows(currentWindow, false)
      val controller = WindowCompat.getInsetsController(currentWindow, view)
      controller.isAppearanceLightStatusBars = !useDarkTheme // Set to true for light icons
    }
  }
}

@Composable
fun GalleryTheme(content: @Composable () -> Unit) {
  val themeOverride = ThemeSettings.themeOverride
  val darkTheme: Boolean =
    (isSystemInDarkTheme() || themeOverride.value == Theme.THEME_DARK) &&
      themeOverride.value != Theme.THEME_LIGHT
  val view = LocalView.current

  StatusBarColorController(useDarkTheme = darkTheme)

  val colorScheme =
    when {
      darkTheme -> darkScheme
      else -> lightScheme
    }

  val customColorsPalette = if (darkTheme) darkCustomColors else lightCustomColors

  CompositionLocalProvider(LocalCustomColors provides customColorsPalette) {
    MaterialTheme(colorScheme = colorScheme, typography = AppTypography, content = content)
  }

  // Make sure the navigation bar stays transparent on manual theme changes.
  LaunchedEffect(darkTheme) {
    val window = (view.context as Activity).window

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      window.isNavigationBarContrastEnforced = false
    }
  }
}
