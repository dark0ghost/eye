package org.dark0ghost.api

interface Api {
    fun getPhoto(): ByteArray

    fun setPhotoFocus(x: Float): Boolean

    fun setSizePhoto(x: Int, y: Int): Boolean

    fun getFocusInformation(): IntArray

}