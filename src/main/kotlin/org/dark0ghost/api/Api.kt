package org.dark0ghost.api

interface Api {

    suspend fun getPhoto(): ByteArray

    suspend fun setPhotoFocus(x: Float): Boolean

    suspend fun setSizePhoto(x: Int, y: Int): Boolean

    suspend fun getFocusInformation(): IntArray
}
