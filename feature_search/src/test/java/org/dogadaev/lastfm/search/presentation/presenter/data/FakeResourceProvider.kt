package org.dogadaev.lastfm.search.presentation.presenter.data

import org.dogadaev.lastfm.statical.resources.ResourceProvider

class FakeResourceProvider: ResourceProvider {

    override fun getString(resourceIdentifier: Int, vararg arguments: Any): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStringArray(resourceIdentifier: Int): Array<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getText(resourceIdentifier: Int): CharSequence {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInteger(resourceIdentifier: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getIntegerArray(resourceIdentifier: Int): Array<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBoolean(resourceIdentifier: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColor(resourceIdentifier: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}