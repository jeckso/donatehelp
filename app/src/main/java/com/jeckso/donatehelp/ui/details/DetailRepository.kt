package com.jeckso.donatehelp.ui.details

import androidx.annotation.WorkerThread
import com.jeckso.donatehelp.data.model.Donate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
) {

    @WorkerThread
    fun getPosterById(id: Long) = flow {
        val poster = Donate.mock()
        emit(poster)
    }.flowOn(Dispatchers.IO)
}