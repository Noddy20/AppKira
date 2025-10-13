package com.nnk.appkira.presentation.features.intro.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import com.nnk.appkira.R
import com.nnk.appkira.presentation.designsystem.color.AppColors
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.features.intro.screens.welcome.WelcomeScreen
import kotlinx.coroutines.launch

@Composable
fun IntroContainerScreen(onFinish: () -> Unit) {
    val pageCount = 3
    val pagerState = rememberPagerState(initialPage = 0) { pageCount }
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(AppDimen.Dimen4X),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PagerIndicator(pageCount = pageCount, currentPage = pagerState.currentPage)

                Spacer(modifier = Modifier.height(AppDimen.Dimen4X))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        if (pagerState.currentPage > 0) {
                            Arrangement.SpaceBetween
                        } else {
                            Arrangement.End
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (pagerState.currentPage > 0) {
                        OutlinedButton(
                            colors =
                                ButtonDefaults.outlinedButtonColors().copy(
                                    contentColor = MaterialTheme.colorScheme.primary,
                                ),
                            border =
                                ButtonDefaults.outlinedButtonBorder().copy(
                                    brush = SolidColor(MaterialTheme.colorScheme.primary),
                                ),
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = pagerState.currentPage - 1,
                                        animationSpec = tween(SLIDE_CHANGE_ANIMATION_DURATION),
                                    )
                                }
                            },
                        ) {
                            Text(text = stringResource(R.string.back))
                        }
                    }

                    Button(
                        colors =
                            ButtonDefaults.buttonColors().copy(
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                        onClick = {
                            if (pagerState.currentPage < pageCount - 1) {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = pagerState.currentPage + 1,
                                        animationSpec = tween(SLIDE_CHANGE_ANIMATION_DURATION),
                                    )
                                }
                            } else {
                                onFinish()
                            }
                        },
                    ) {
                        Text(
                            text =
                                stringResource(
                                    if (pagerState.currentPage < pageCount - 1) {
                                        R.string.next
                                    } else {
                                        R.string.finish
                                    },
                                ),
                        )
                    }
                }
            }
        },
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) { page ->
            when (page) {
                0 -> {
                    WelcomeScreen()
                }

                1 -> {
                    Text("Page $page")
                }

                2 -> {
                    Text("Page $page")
                }
            }
        }
    }
}

private const val SLIDE_CHANGE_ANIMATION_DURATION = 500

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) {
                    MaterialTheme.colorScheme.primary
                } else {
                    AppColors.LightDark
                }
            Box(
                modifier =
                    Modifier
                        .padding(AppDimen.DimenX)
                        .clip(CircleShape)
                        .background(color)
                        .size(AppDimen.Dimen2X),
            )
        }
    }
}
