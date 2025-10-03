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
            primary = AppColors.Dark,
            secondary = AppColors.DarkX,
            tertiary = AppColors.Green,
        )
private val lightColorScheme: ColorScheme
    get() =
        lightColorScheme(
            primary = AppColors.Light,
            secondary = AppColors.LightX,
            tertiary = AppColors.Green,
        )

private val typography: Typography
    get() =
        Typography(
            bodyLarge =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = FontSize.FontSizeNormal,
                    lineHeight = FontSize.LineHeight,
                    letterSpacing = FontSize.LetterSpacing,
                ),
        /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
         */
        )

@Composable
fun AppKiraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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
