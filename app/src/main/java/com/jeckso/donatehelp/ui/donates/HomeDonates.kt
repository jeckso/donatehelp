package com.jeckso.donatehelp.ui.donates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jeckso.donatehelp.data.model.Donate
import com.jeckso.donatehelp.ui.theme.DonateHelpTheme
import com.jeckso.donatehelp.ui.utils.NetworkImage


@Composable
fun HomeDonates(
    modifier: Modifier = Modifier,
    donates: List<Donate>,
    selectDonate: (Long) -> Unit,
) {
    Box(modifier = modifier){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(4.dp),
            content = {
                items(donates.size) { index ->
                    HomeDonate(
                        donate = donates[index],
                        selectDonate = selectDonate
                    )
                }
            }
        )
    }

}

@Composable
private fun HomeDonate(
    modifier: Modifier = Modifier,
    donate: Donate,
    selectDonate: (Long) -> Unit = {},
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { selectDonate(donate.id) }
            ),
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title, content) = createRefs()
            NetworkImage(
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                url = donate.image,
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
                    .padding(8.dp),
                text = donate.name,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview(name = "HomePoster Light Theme")
private fun HomePosterPreviewLight() {
    DonateHelpTheme(darkTheme = false) {
        HomeDonate(
            donate = Donate.mock()
        )
    }
}