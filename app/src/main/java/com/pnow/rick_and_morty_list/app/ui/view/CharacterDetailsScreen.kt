package com.pnow.rick_and_morty_list.app.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import com.pnow.rick_and_morty_list.app.ui.model.DetailsUIModel

@Composable
fun CharacterDetailsScreen(
    character: CharacterUIModel,
    detailsState: DetailsUIModel,
    onFetchDetails: () -> Unit
) {
    LaunchedEffect(Unit) {
        onFetchDetails()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${character.name}")
        Text(text = "Species: ${character.species}")
        Text(text = "Gender: ${character.gender}")
        Text(text = "Status: ${character.status}")

        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = rememberAsyncImagePainter(character.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Origin: ${detailsState.originModel.name}")
        Text("Location: ${detailsState.locationModel.name}")

        Spacer(modifier = Modifier.height(8.dp))
        Text("Episodes:")
        detailsState.episodeModel.forEach {
            Text(
                text = it.nameAndNumbering,
                modifier = Modifier.padding(vertical = 2.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

