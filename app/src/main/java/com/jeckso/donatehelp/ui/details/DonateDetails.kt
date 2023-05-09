package com.jeckso.donatehelp.ui.details

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import com.jeckso.donatehelp.data.model.Donate
import com.jeckso.donatehelp.extensions.paletteColorList
import com.jeckso.donatehelp.ui.theme.highlightGreen
import com.jeckso.donatehelp.ui.utils.NetworkImage
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.rememberPaletteState

@Composable
fun DonateDetails(
    donateId: Long,
    viewModel: DetailViewModel,
    pressOnBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = donateId) {
        viewModel.loadPosterById(donateId)
    }

    val details: Donate? by viewModel.posterDetailsFlow.collectAsState(initial = null)
    details?.let { poster ->
        PosterDetailsBody(poster, pressOnBack)
    }
}

@Composable
private fun PosterDetailsBody(
    donate: Donate,
    pressOnBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(highlightGreen)
            .fillMaxHeight()
    ) {
        var palette by rememberPaletteState(value = null)

        ConstraintLayout {
            val (arrow, image, paletteRow, title, content, gifTitle, gif) = createRefs()

            NetworkImage(
                url = donate.image,
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .aspectRatio(0.85f),
                circularRevealEnabled = true,
                paletteLoadedListener = { palette = it }
            )
            Text(
                text = donate.name,
                style = MaterialTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                    }
                    .padding(start = 16.dp, top = 12.dp)
            )

            Text(
                text = donate.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(title.bottom)
                    }
                    .padding(16.dp)
            )

            Text(
                text = "Gif",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(gifTitle) {
                        top.linkTo(content.bottom)
                    }
            )

            CoilImage(
                imageModel = { donate.image },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(500.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(gif) {
                        top.linkTo(gifTitle.bottom)
                    },
            )

//            ImageBalloonAnchor(
//                reference = image,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(0.85f),
//                content = poster.name,
//                onClick = { balloon, anchor -> balloon.showAlignBottom(anchor) }
//            )

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.Black,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(arrow) {
                        top.linkTo(parent.top)
                    }
                    .padding(12.dp)
                    .statusBarsPadding()
                    .clickable(onClick = { pressOnBack() })
            )
        }
    }
}