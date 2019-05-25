package org.dogadaev.lastfm.statical.mvp

import androidx.annotation.CallSuper
import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<View : BaseView> : MvpPresenter<View>(), CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineName(javaClass.simpleName)

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}