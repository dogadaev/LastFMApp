package org.dogadaev.lastfm.search.presentation.view.fragment

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.dogadaev.lastfm.search.presentation.contract.Search
import org.dogadaev.lastfm.statical.base.BaseFragment
import org.koin.android.ext.android.get

class SearchFragment : BaseFragment(), Search.View {

    @InjectPresenter
    internal lateinit var presenter: Search.Presenter

    @ProvidePresenter
    fun provide(): Search.Presenter = get()

    override fun onState(state: Search.State) {
    }
}