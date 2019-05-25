package org.dogadaev.lastfm.navigation

import android.os.Parcelable
import ru.terrakok.cicerone.Screen

abstract class LastFMScreen : Screen(), Parcelable

/**
 * @property BaseMainScreen - Main screen container.
 * @property MainScreen - All locally stored albums.
 * @property DetailsScreen - A detail-page about album.
 */
abstract class BaseMainScreen : LastFMScreen()
abstract class MainScreen : LastFMScreen()
abstract class DetailsScreen : LastFMScreen()

/**
 * @property BaseSearchScreen - Search screen container.
 * @property SearchScreen - Search for an artist on the LastFMApi.
 */
abstract class BaseSearchScreen : LastFMScreen()
abstract class SearchScreen : LastFMScreen()

/**
 * @property BaseAlbumsScreen - Albums screen container.
 * @property AlbumsScreen - Best albums of an artist.
 */
abstract class BaseAlbumsScreen : LastFMScreen()
abstract class AlbumsScreen : LastFMScreen()