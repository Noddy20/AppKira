package com.nnk.appkira.presentation.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.nnk.appkira.presentation.designsystem.color.AppColors
import com.nnk.appkira.presentation.designsystem.dimen.FontSize

private val darkColorScheme: ColorScheme
    get() =
        darkColorScheme(
            primary = AppColors.Green,
            onPrimary = AppColors.White,
            background = AppColors.DarkBackground,
            onBackground = AppColors.OnDarkMain,
            surface = AppColors.DarkBackground,
            onSurface = AppColors.OnDarkVar,
            onError = AppColors.Red,
        )
private val lightColorScheme: ColorScheme
    get() =
        lightColorScheme(
            primary = AppColors.Green,
            onPrimary = AppColors.White,
            background = AppColors.LightBackground,
            onBackground = AppColors.OnLightMain,
            surface = AppColors.LightBackground,
            surfaceTint = AppColors.Transparent,
            onSurface = AppColors.OnLightVar,
            onError = AppColors.Red,
        )

private val typography: Typography
    get() =
        Typography(
            titleLarge =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.TitleLarge,
                    lineHeight = FontSize.TitleLineHeight,
                    letterSpacing = FontSize.TitleLetterSpacing,
                ),
            titleMedium =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.TitleMedium,
                    lineHeight = FontSize.TitleLineHeight,
                    letterSpacing = FontSize.TitleLetterSpacing,
                ),
            titleSmall =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.TitleSmall,
                    lineHeight = FontSize.TitleLineHeight,
                    letterSpacing = FontSize.TitleLetterSpacing,
                ),
            bodyLarge =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.BodyLarge,
                    lineHeight = FontSize.BodyLineHeight,
                    letterSpacing = FontSize.BodyLetterSpacing,
                ),
            bodyMedium =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.BodyMedium,
                    lineHeight = FontSize.BodyLineHeight,
                    letterSpacing = FontSize.BodyLetterSpacing,
                ),
            bodySmall =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.BodySmall,
                    lineHeight = FontSize.BodyLineHeight,
                    letterSpacing = FontSize.BodyLetterSpacing,
                ),
            labelSmall =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.LabelSmall,
                    lineHeight = FontSize.LabelLineHeight,
                    letterSpacing = FontSize.LabelLetterSpacing,
                ),
        )

@Composable
fun AppKiraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> darkColorScheme
            else -> lightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
    )
}
