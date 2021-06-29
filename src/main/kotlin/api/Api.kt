package api

interface Api {
    fun getPhoto(): ByteArray

    fun setPhotoFocus(x: Int, y: Int): Boolean

    fun setSizePhoto(x: Int, y: Int): Boolean

    fun getFocusInformation(): IntArray
}