package com.franciscogarciagarzon.learningpath.ui.screens.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.franciscogarciagarzon.learningpath.ui.MainActivity
import com.franciscogarciagarzon.learningpath.ui.theme.LearningPathTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = { },
    onRetry: (Any?) -> Unit = {},
    message: String = "SOME ERROR "
) {
    val activity = (LocalContext.current as? MainActivity)
    LearningPathTheme {
        Surface(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            AlertDialog(
                onDismissRequest = onDismissRequest,
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .wrapContentSize(
                        align = Alignment.Center
                    )
                    .padding(5.dp),
                properties = DialogProperties(dismissOnClickOutside = false),
                content = {

                    Column(
                        modifier = modifier
                            .wrapContentHeight(
                                unbounded = false,
                                align = Alignment.CenterVertically
                            )
                            .clip(shape = RoundedCornerShape(20.dp))
                            .padding(top = 10.dp, bottom = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = modifier
                                .wrapContentHeight()
                                .fillMaxWidth(),
                            text = message,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Row(
                            modifier = modifier
                                .wrapContentHeight()
                                .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Button(
                                modifier = modifier.weight(0.4f),
                                onClick = {
                                    Log.d("PokemonList", "errorDialog retry button")
                                    onRetry(null)
                                },
                            ) {
                                Text(text = "Retry")
                            }
                            Spacer(modifier = modifier.weight(0.1f))
                            Button(
                                modifier = modifier
                                    .weight(0.4f),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                                onClick = {
                                    Log.d("PokemonList", "errorDialog close button")
                                    activity?.finish()
                                },
                            ) {
                                Text(text = "Close")
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
@Preview(
    name = "NEXUS_6", device = Devices.NEXUS_6,
    showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun PreviewDialog() {
    LearningPathTheme {
        Surface {
            ErrorDialog()
        }

    }
}
