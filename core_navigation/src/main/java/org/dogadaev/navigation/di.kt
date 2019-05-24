package org.dogadaev.navigation

import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

val navigationModule = module {
    val cicerone = Cicerone.create()

    single { cicerone.router }
    single { cicerone.navigatorHolder }
}