package com.fionasiregar0032.eventvault.ui.screen
import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fionasiregar0032.eventvault.R
import com.fionasiregar0032.eventvault.model.Event
import com.fionasiregar0032.eventvault.ui.theme.EventVaultTheme

@Composable
fun EventDialog(
    bitmap: Bitmap?,
    initialEvent: Event? = null,
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String, String) -> Unit
) {
    var nama_kegiatan by remember { mutableStateOf("") }
    var deskripsi_kegiatan by remember { mutableStateOf("") }
    var tanggal_kegiatan by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                )
                OutlinedTextField(
                    value = nama_kegiatan,
                    onValueChange = { nama_kegiatan = it },
                    label = { Text(text = stringResource(id = R.string.nama_kegiatan)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                OutlinedTextField(
                    value = deskripsi_kegiatan,
                    onValueChange = { deskripsi_kegiatan = it },
                    label = { Text(text = stringResource(id = R.string.deskripsi_kegiatan)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                OutlinedTextField(
                    value = tanggal_kegiatan,
                    onValueChange = { tanggal_kegiatan = it },
                    label = { Text(text = stringResource(id = R.string.tanggal_kegiatan)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done

                ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = stringResource(id = R.string.batal))
                    }
                    OutlinedButton(
                        onClick = {
                            onConfirmation(nama_kegiatan, deskripsi_kegiatan, tanggal_kegiatan)
                        },
                        enabled = nama_kegiatan.isNotEmpty() &&
                                deskripsi_kegiatan.isNotEmpty() &&
                                tanggal_kegiatan.isNotEmpty(),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = stringResource(id = R.string.simpan))

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EventDialogPreview() {
    EventVaultTheme {
        EventDialog(
            bitmap = null,
            onDismissRequest = {},
            onConfirmation = { _, _, _ -> }
        )
    }
}