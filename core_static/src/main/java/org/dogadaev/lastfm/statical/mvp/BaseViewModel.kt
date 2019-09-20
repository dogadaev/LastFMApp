package org.dogadaev.lastfm.statical.mvp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineName(javaClass.simpleName)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}