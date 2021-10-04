package org.dark0ghost.eye

import org.dark0ghost.ai.Ai
import org.dark0ghost.api.Api
import org.dark0ghost.exceptions.eyeException.ApiNotSetException

class Eye(private val api: org.dark0ghost.api.Api, private val ai: Ai) {
    private suspend fun getPhoto(): Int = api.getPhoto()

    private suspend fun <T : Number> setSize(x: T, y: T): Boolean = api.setSizePhoto(x.toInt(), y.toInt())

    private suspend fun <T : Number> setFocus(x: T): Boolean = api.setPhotoFocus(x.toFloat())

    private suspend fun getFocus(): IntArray = api.getFocusInformation()

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

        fun build(): Eye {
            api?.let { apis: Api ->
                machine?.let { ai: Ai ->
                    return Eye(apis, ai)
                }
                return Eye(apis, Ai())
            }
            throw ApiNotSetException("set Api for work eye")
        }
    }
}
