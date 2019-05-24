package org.dogadaev.statical.resources

import android.content.Context
import androidx.annotation.*
import org.dogadaev.statical.extensions.getColorCompat

class AndroidResourceProvider(private val context: Context) : ResourceProvider {
    override fun getString(@StringRes resourceIdentifier: Int, vararg arguments: Any): String {
        return if (arguments.isNotEmpty()) {
            context.resources.getString(resourceIdentifier, *arguments)
        } else {
            context.resources.getString(resourceIdentifier)
        }
    }

    override fun getStringArray(@ArrayRes resourceIdentifier: Int): Array<String> =
        context.resources.getStringArray(resourceIdentifier)

    override fun getText(resourceIdentifier: Int): CharSequence =
        context.resources.getText(resourceIdentifier)

    override fun getInteger(@IntegerRes resourceIdentifier: Int): Int =
        context.resources.getInteger(resourceIdentifier)

    override fun getIntegerArray(@ArrayRes resourceIdentifier: Int): Array<Int> =
        context.resources.getIntArray(resourceIdentifier).toTypedArray()


    override fun getBoolean(@BoolRes resourceIdentifier: Int): Boolean =
        context.resources.getBoolean(resourceIdentifier)

    override fun getColor(@ColorRes resourceIdentifier: Int): Int =
        context.getColorCompat(resourceIdentifier)
}