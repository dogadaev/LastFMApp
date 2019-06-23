package org.dogadaev.lastfm.search.presentation.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.dogadaev.lastfm.search.data.repository.SearchRepository
import org.dogadaev.lastfm.search.presentation.contract.Search
import org.dogadaev.lastfm.statical.resources.ResourceProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class SearchPresenterTest {

    private val view: Search.View = mockk(relaxed = true)

    lateinit var presenter: Search.Presenter

    private val searchRepository: SearchRepository = mockk()

    private val resourceProvider: ResourceProvider = mockk()

    @Before
    fun before() {
        presenter = SearchPresenter(searchRepository, mockk(), resourceProvider)
        presenter.attachView(view)
    }

    @Test
    fun `is new search performed`() {
        every {
            runBlocking { searchRepository.searchForArtist(any(), any(), any()) }
        } returns mockk(relaxed = true)

        val query = "Macklemore"
        presenter.performNewSearch(query)

        val viewStateSlot = slot<Search.State.OnDisplay>()
        verify { view.onState(capture(viewStateSlot)) }
    }
}