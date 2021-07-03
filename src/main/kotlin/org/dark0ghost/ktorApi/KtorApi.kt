package org.dark0ghost.ktorApi

import org.dark0ghost.api.Api
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.*
import java.net.InetSocketAddress
import java.util.concurrent.Executors

class KtorApi(private val socket: Socket) : Api {

    override fun getFocusInformation(): IntArray {
        TODO("Not yet implemented")
    }

    override fun getPhoto(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun setPhotoFocus(x: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSizePhoto(x: Int, y: Int): Boolean {
        TODO("Not yet implemented")
    }

    data class Builder(
        private var executors: Executors? = null,

        @OptIn(KtorExperimentalAPI::class)
        private var selectors: ActorSelectorManager? = null,
        private var tcpSocketBuilders: TcpSocketBuilder? = null,
        private var isSetSocket: Boolean = false,
        private var address: InetSocketAddress? = null
    ) {

        private lateinit var clientSocket: Socket

        fun setExecutor(exec: Executors) = apply { executors = exec }

        @OptIn(KtorExperimentalAPI::class)
        fun setSelector(selector: ActorSelectorManager) = apply { selectors = selector }


        fun setTcpBuilder(tcpSocketBuilder: TcpSocketBuilder) = apply { tcpSocketBuilders = tcpSocketBuilder }

        fun setClientSocket(socket: Socket) = apply {
            clientSocket = socket
            isSetSocket = true
        }

        fun setAddress(adr: InetSocketAddress) = apply {
            address = adr
        }

        @OptIn(KtorExperimentalAPI::class)
        suspend fun build(): KtorApi {
            if (isSetSocket) {
                return KtorApi(clientSocket)
            }
            if (address != null) {
                selectors?.let {
                    clientSocket = address?.let { inetSocketAddress -> aSocket(it).tcp().connect(inetSocketAddress) }
                        ?: aSocket(it).tcp().connect(InetSocketAddress("127.0.0.1", 2323))
                    return KtorApi(clientSocket)
                }
                tcpSocketBuilders?.let {
                    clientSocket =
                        address?.let { inetSocketAddress -> return@let it.connect(inetSocketAddress) } ?: it.connect(
                            InetSocketAddress("127.0.0.1", 2323)
                        )
                    return KtorApi(clientSocket)
                }

            }
            throw NullPointerException("address is null")
        }

    }
}
