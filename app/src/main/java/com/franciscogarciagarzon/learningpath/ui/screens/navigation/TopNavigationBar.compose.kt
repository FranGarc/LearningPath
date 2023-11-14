package com.franciscogarciagarzon.learningpath.ui.screens.navigation

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.franciscogarciagarzon.learningpath.ui.extensions.capitalizeLP
import com.franciscogarciagarzon.learningpath.ui.screens.components.RegularLabel
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme


@OptIn(ExperimentalMaterial3Api::class)
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
            scrolledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.White
        ),
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        title = {
            RegularLabel(
                modifier = Modifier.fillMaxWidth(),
                text = title.capitalizeLP(),
                textAlignment = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(onClick = upNavigation) {
                Icon(Icons.Default.ArrowBack, "Back")
            }
        },

        )
}

