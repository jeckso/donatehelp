package com.jeckso.donatehelp.data.model

data class Donate (
    val name: String,
    val id: Long,
    val description: String,
    val image: String,
){
    companion object {

        fun mock() = Donate(
            id = 0,
            name = "Mavic",
            description = "Collect money for mavic 3 for TRO",
            image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTEwLgigjjYI1D518XH0atGHWOEEhkoiM7S8LlbkVkKZXE-j4jvnZXnyKAWxMxc4gYF5Sx8hP4WtXA3TF9iBQuK6cZwG6NYGhLaj66L6SrDeNa_-l--w9_BwD3TvO5zJGiwrw&usqp=CAc"
        )
    }
}
