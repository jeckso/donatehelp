package com.jeckso.donatehelp.ui.donates

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jeckso.donatehelp.R
import com.jeckso.donatehelp.data.model.Donate
import com.jeckso.donatehelp.ui.main.MainViewModel
import com.jeckso.donatehelp.ui.theme.highlightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Donates(
    viewModel: MainViewModel,
    selectDonate: (Long) -> Unit
) {
    val donates: List<Donate> by viewModel.donatesList.collectAsState(initial = listOf())

    val isLoading: Boolean by viewModel.isLoading
    val selectedTab = DonatesHomeTab.getTabFromResource(viewModel.selectedTab.value)
    val tabs = DonatesHomeTab.values()

    ConstraintLayout {
        val (body, progress) = createRefs()

        Scaffold(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            topBar = { PosterAppBar() },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
            bottomBar = {
                NavigationBar(
                    containerColor = highlightGreen,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                            label = { Text(text = stringResource(tab.title), color = Color.White) },
                            selected = tab == selectedTab,
                            onClick = { viewModel.selectTab(tab.title) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = LocalContentColor.current,
                                unselectedTextColor = LocalContentColor.current
                            )
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Crossfade(selectedTab) { destination ->
                when (destination) {
                    DonatesHomeTab.HOME -> HomeDonates(modifier, donates, selectDonate)
                    DonatesHomeTab.FEATURED -> HomeDonates(modifier, donates, selectDonate)
                    DonatesHomeTab.LIKES -> HomeDonates(modifier, donates, selectDonate)
                }
            }
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .constrainAs(progress) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PosterAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = highlightGreen),
        modifier = Modifier
            .statusBarsPadding()
            .height(58.dp),
        title = {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = stringResource(R.string.app_name),
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    )
}


enum class DonatesHomeTab(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(R.string.menu_home, Icons.Filled.Home),
    FEATURED(R.string.menu_featured, Icons.Filled.Info),
    LIKES(R.string.menu_likes, Icons.Filled.Favorite);

    companion object {
        fun getTabFromResource(@StringRes resource: Int): DonatesHomeTab {
            return when (resource) {
                R.string.menu_featured -> FEATURED
                R.string.menu_likes -> LIKES
                else -> HOME
            }
        }
    }
}