package org.dogadaev.statical.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.dogadaev.statical.androidx.moxy.MvpAppCompatFragment
import org.dogadaev.statical.mvp.BaseView

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {
    protected open val layoutRes: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (layoutRes != 0) {
            inflater.inflate(layoutRes, container, false)
        } else {
            null
        }
    }
}