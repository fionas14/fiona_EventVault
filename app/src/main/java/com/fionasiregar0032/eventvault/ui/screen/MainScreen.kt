package com.fionasiregar0032.eventvault.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fionasiregar0032.eventvault.R
import com.fionasiregar0032.eventvault.ui.theme.EventVaultTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    Scaffold(
        containerColor = Color(0xFFD8BFD8),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFC8A2C8),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))

    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()

    Text(
        text = "Event Vault",
        modifier = modifier
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,showBackground = true)
@Composable
fun MainScreenPreview() {
    EventVaultTheme {
        MainScreen()
    }
}