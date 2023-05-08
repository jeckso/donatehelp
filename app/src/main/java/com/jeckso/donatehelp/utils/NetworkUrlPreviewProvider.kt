package com.jeckso.donatehelp.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NetworkUrlPreviewProvider : PreviewParameterProvider<String> {
    override val count: Int
        get() = super.count
    override val values: Sequence<String>
        get() = sequenceOf("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTEwLgigjjYI1D518XH0atGHWOEEhkoiM7S8LlbkVkKZXE-j4jvnZXnyKAWxMxc4gYF5Sx8hP4WtXA3TF9iBQuK6cZwG6NYGhLaj66L6SrDeNa_-l--w9_BwD3TvO5zJGiwrw&usqp=CAc")
}