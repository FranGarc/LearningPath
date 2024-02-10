package com.franciscogarciagarzon.learningpath.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingIndicator() {
    LearningPathTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(260.dp)
                        .padding(20.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeWidth = 15.dp
                )
            }
        }
    }
}

@Composable
@Preview(
    name = "NEXUS_6", device = Devices.NEXUS_6,
    showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun PreviewLoading() {
    LearningPathTheme {
        Surface {
            LoadingIndicator()
        }

    }
}