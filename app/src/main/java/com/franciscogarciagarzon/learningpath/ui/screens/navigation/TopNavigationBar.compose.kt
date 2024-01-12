package com.franciscogarciagarzon.learningpath.ui.screens.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import com.franciscogarciagarzon.learningpath.ui.extensions.capitalizeLP
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun TopNavBar(
    modifier: Modifier = Modifier,
    title: String = "pokename",
    upNavigation: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.surfaceTint,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier.background(color = MaterialTheme.colorScheme.primaryContainer),
        title = {
            RegularLabel(
                modifier = Modifier
                    .basicMarquee()
                    .fillMaxWidth(),
                text = title.capitalizeLP(),
                textAlignment = TextAlign.Center,
            )
        },
        navigationIcon = {
            val lifecycleOwner = LocalLifecycleOwner.current
            IconButton(
                onClick = {
                    val currentState = lifecycleOwner.lifecycle.currentState
                    // fix for the bug with doubletapping leading to blank screen
                    // when the lifecycle current state is RESUMED, it's not navigating
                    // so we ignore any click events when we've started navigating to another screen
                    if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                        upNavigation()
                    }

                }

            ) {
                Icon(Icons.Default.ArrowBack, "Back")
            }
        },

        )
}

