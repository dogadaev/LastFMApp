package org.dogadaev.statical.gson

import com.google.gson.Gson
import org.koin.dsl.module

val gsonModule = module {
    single { Gson() }
}