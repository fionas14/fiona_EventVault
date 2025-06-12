package com.fionasiregar0032.eventvault.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fionasiregar0032.eventvault.R
import com.fionasiregar0032.eventvault.model.Event
import com.fionasiregar0032.eventvault.network.EventApi
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
    val data by viewModel.data.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Event Vault",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(data) { event ->
                EventItem(event = event)
            }
        }
    }
}

@Composable
 fun EventItem(event: Event) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .border(1.dp, Color.Gray)
    ) {
        ListItem(
            headlineContent = { Text(text = event.nama_kegiatan) },
            supportingContent = { Text(text = event.deskripsi_kegiatan) }
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(EventApi.getEventUrl(event.imageId))
                .crossfade(true)
                .build(),
            contentDescription = "Gambar ${event.nama_kegiatan}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,showBackground = true)
@Composable
fun MainScreenPreview() {
    EventVaultTheme {
        MainScreen()
    }
}