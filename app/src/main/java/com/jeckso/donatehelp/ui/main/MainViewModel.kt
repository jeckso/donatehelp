package com.jeckso.donatehelp.ui.main

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jeckso.donatehelp.data.model.Donate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val donatesList: Flow<List<Donate>> =
        flow {
            val list = listOf(
                Donate(
                    id = 0,
                    name = "Mavic",
                    description = "Collect money for mavic 3 for TRO",
                    image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTEwLgigjjYI1D518XH0atGHWOEEhkoiM7S8LlbkVkKZXE-j4jvnZXnyKAWxMxc4gYF5Sx8hP4WtXA3TF9iBQuK6cZwG6NYGhLaj66L6SrDeNa_-l--w9_BwD3TvO5zJGiwrw&usqp=CAc",
                ), Donate(
                    id = 0,
                    name = "Mavic",
                    description = "Collect money for mavic 3 for TRO",
                    image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTEwLgigjjYI1D518XH0atGHWOEEhkoiM7S8LlbkVkKZXE-j4jvnZXnyKAWxMxc4gYF5Sx8hP4WtXA3TF9iBQuK6cZwG6NYGhLaj66L6SrDeNa_-l--w9_BwD3TvO5zJGiwrw&usqp=CAc",
                ), Donate(
                    id = 0,
                    name = "Mavic",
                    description = "Collect money for mavic 3 for TRO",
                    image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTEwLgigjjYI1D518XH0atGHWOEEhkoiM7S8LlbkVkKZXE-j4jvnZXnyKAWxMxc4gYF5Sx8hP4WtXA3TF9iBQuK6cZwG6NYGhLaj66L6SrDeNa_-l--w9_BwD3TvO5zJGiwrw&usqp=CAc",
                ), Donate(
                    id = 0,
                    name = "Mavic",
                    description = "Collect money for mavic 3 for TRO",
                    image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTEwLgigjjYI1D518XH0atGHWOEEhkoiM7S8LlbkVkKZXE-j4jvnZXnyKAWxMxc4gYF5Sx8hP4WtXA3TF9iBQuK6cZwG6NYGhLaj66L6SrDeNa_-l--w9_BwD3TvO5zJGiwrw&usqp=CAc",
                ), Donate(
                    id = 0,
                    name = "Mavic",
                    description = "Collect money for mavic 3 for TRO",
                    image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTEwLgigjjYI1D518XH0atGHWOEEhkoiM7S8LlbkVkKZXE-j4jvnZXnyKAWxMxc4gYF5Sx8hP4WtXA3TF9iBQuK6cZwG6NYGhLaj66L6SrDeNa_-l--w9_BwD3TvO5zJGiwrw&usqp=CAc",
                )
            )
            emit(list)
        }


    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    init {
        Timber.e("injection MainViewModel")
    }

    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }
}