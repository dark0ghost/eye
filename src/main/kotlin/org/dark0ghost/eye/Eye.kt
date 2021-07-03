package org.dark0ghost.eye

import org.dark0ghost.ai.Ai
import org.dark0ghost.api.Api

class Eye(private val api: org.dark0ghost.api.Api, private val ai: Ai) {
    private fun getPhoto(): ByteArray = api.getPhoto()

    private fun <T : Number> setSize(x: T, y: T): Boolean = api.setSizePhoto(x.toInt(), y.toInt())

    private fun <T : Number> setFocus(x: T): Boolean = api.setPhotoFocus(x.toFloat())

    private fun getFocus(): IntArray = api.getFocusInformation()

    data class Builder(private var api: Api? = null, private var machine: Ai? = null) {

        fun setApi(ap: Api) = apply {
            api = ap
        }

        fun setAI(ai: Ai) = apply {
            machine = ai
        }

        fun setAIWithInit(init: Ai.() -> Unit) = apply {
            val machineInit = Ai()
            machineInit.init()
            machine = machineInit
        }

        fun build() {

        }

    }
}
