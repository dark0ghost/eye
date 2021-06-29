package ktorApi

import api.Api

sealed class KtorApi() : Api {

    override fun getFocusInformation(): IntArray {
        TODO("Not yet implemented")
    }

    override fun getPhoto(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun setPhotoFocus(x: Int, y: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSizePhoto(x: Int, y: Int): Boolean {
        TODO("Not yet implemented")
    }

}