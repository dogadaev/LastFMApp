package org.dogadaev.lastfm.start.presentation.view.activity

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.dogadaev.lastfm.start.presentation.contract.Start
import org.dogadaev.lastfm.statical.base.BaseActivity
import org.koin.android.ext.android.get

class StartActivity : BaseActivity(), Start.View {
    @InjectPresenter
    internal lateinit var presenter: Start.Presenter

    @ProvidePresenter
    fun provide(): Start.Presenter = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.handleIntent(intent)
    }
}
