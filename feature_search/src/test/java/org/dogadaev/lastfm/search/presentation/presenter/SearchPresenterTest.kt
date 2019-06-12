package org.dogadaev.lastfm.search.presentation.presenter

import org.dogadaev.lastfm.search.presentation.contract.Search
import org.dogadaev.lastfm.search.presentation.contract.`Search$View$$State`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchPresenterTest : KoinTest {

    @Mock
    lateinit var view: Search.View

    @Mock
    lateinit var viewState: `Search$View$$State`


    private val presenter: Search.Presenter by inject()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(searchTestModule)
        }

        presenter.attachView(view)
        presenter.setViewState(viewState)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `Test search`() {
        // todo: implement test logic
    }
}