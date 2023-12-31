package com.jeckso.donatehelp.extensions

import androidx.palette.graphics.Palette

fun Palette?.paletteColorList(): List<Int> {
    return listOf(
        this?.lightVibrantSwatch?.rgb,
        this?.lightMutedSwatch?.rgb,
        this?.vibrantSwatch?.rgb,
        this?.mutedSwatch?.rgb,
        this?.darkVibrantSwatch?.rgb,
        this?.darkMutedSwatch?.rgb,
        this?.dominantSwatch?.rgb
    ).map { it ?: 0 }
}
