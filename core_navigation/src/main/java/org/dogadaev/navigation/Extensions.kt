package org.dogadaev.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <reified S : LastFMScreen> Activity.getScreen(): S {
    return requireNotNull(intent?.extras?.getParcelable(S::class.java.name))
}

inline fun <reified S : LastFMScreen> Fragment.getScreen(): S {
    return requireNotNull(arguments?.getParcelable(S::class.java.name))
}

inline fun <reified T : Fragment, reified S : LastFMScreen> S.newFragmentWithScreen(): T {
    val fragment = T::class.java.newInstance()
    val bundle = Bundle()
    bundle.putParcelable(S::class.java.name, this)
    fragment.arguments = bundle
    return fragment
}

inline fun <reified T : Activity, reified S : LastFMScreen> S.newIntentWithScreen(context: Context): Intent {
    val intent = Intent(context, T::class.java)
    intent.putExtra(S::class.java.name, this)
    return intent
}