package com.fionasiregar0032.eventvault.ui.screen

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fionasiregar0032.eventvault.model.Event

@Composable
fun EditEvent(
    event: Event,
    onUpdate: (String, String, String, Bitmap?) -> Unit,
    onBack: () -> Unit
) {
    var nama_kegiatan by remember { mutableStateOf(event.nama_kegiatan) }
    var deskripsi_kegiatan by remember { mutableStateOf(event.deskripsi_kegiatan) }
    var tanggal_kegiatan by remember { mutableStateOf(event.tanggal_kegiatan) }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    Dialog(onDismissRequest = onBack) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Edit Event", style = MaterialTheme.typography.titleLarge)

                OutlinedTextField(
                    value = nama_kegiatan,
                    onValueChange = { nama_kegiatan = it },
                    label = { Text("Nama Kegiatan") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = deskripsi_kegiatan,
                    onValueChange = { deskripsi_kegiatan = it },
                    label = { Text("Deskripsi") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = tanggal_kegiatan,
                    onValueChange = { tanggal_kegiatan = it },
                    label = { Text("Tanggal") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = onBack) {
                        Text("Batal")
                    }
                    Button(onClick = {
                        onUpdate(nama_kegiatan, deskripsi_kegiatan, tanggal_kegiatan, imageBitmap)
                        onBack()
                    }) {
                        Text("Simpan")
                    }
                }
            }
        }
    }
}


