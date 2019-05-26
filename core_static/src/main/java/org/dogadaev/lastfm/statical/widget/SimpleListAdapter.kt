package org.dogadaev.lastfm.statical.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import org.dogadaev.lastfm.statical.widget.SimpleListAdapter.ViewHolder
import org.koin.core.KoinComponent

private typealias OnBottomReachListener = () -> Unit

abstract class SimpleListAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, ViewHolder>(diffCallback), KoinComponent {

    abstract val layoutRes: Int
    protected open val bottomReachLimit: Int = 1
    private var onBottomReachListener: OnBottomReachListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layoutRes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == itemCount - bottomReachLimit) onBottomReachListener?.invoke()
        bind(position, holder)
    }

    abstract fun bind(position: Int, holder: ViewHolder)

    fun onBottomReached(listener: OnBottomReachListener?) {
        onBottomReachListener = listener
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}