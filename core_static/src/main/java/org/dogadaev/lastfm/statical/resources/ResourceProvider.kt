package org.dogadaev.lastfm.statical.resources

import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.ColorRes
import androidx.annotation.IntegerRes

interface ResourceProvider {
    fun getString(resourceIdentifier: Int, vararg arguments: Any = arrayOf()): String

    fun getStringArray(@ArrayRes resourceIdentifier: Int): Array<String>

    fun getText(resourceIdentifier: Int): CharSequence

    fun getInteger(@IntegerRes resourceIdentifier: Int): Int

    fun getIntegerArray(@ArrayRes resourceIdentifier: Int): Array<Int>

    fun getBoolean(@BoolRes resourceIdentifier: Int): Boolean

    fun getColor(@ColorRes resourceIdentifier: Int): Int
}