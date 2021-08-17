package org.dark0ghost.ktorApi

import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.network.sockets.TcpSocketBuilder
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.writeStringUtf8
import org.dark0ghost.api.Api
import org.dark0ghost.enums.ApiCommand
import org.dark0ghost.exceptions.apiKtorException.AddressNotSet
import org.dark0ghost.exceptions.apiKtorException.FailConnect
import java.net.InetSocketAddress
import java.util.concurrent.Executors

open class KtorApi(socket: Socket) : Api {

    private val input: ByteReadChannel = socket.openReadChannel()
    private val output: ByteWriteChannel = socket.openWriteChannel(autoFlush = true)

    override suspend fun getFocusInformation(): IntArray {
        TODO("Not yet implemented")
    }

    override suspend fun getPhoto(): Byte {
        output.writeStringUtf8(ApiCommand.Send.name)
        return input.readByte()
    }

    override suspend fun setPhotoFocus(x: Float): Boolean {
        output.writeStringUtf8("${ApiCommand.SetFocus.name}${x}f\n")
        return input.readBoolean()
    }

    override suspend fun setSizePhoto(x: Int, y: Int): Boolean {
        output.writeStringUtf8("${ApiCommand.SetSizePhoto.name}$x:$y\n")
        return input.readBoolean()
    }

    data class Builder(
        private var executors: Executors? = null,
        private var selectors: ActorSelectorManager? = null,
        private var tcpSocketBuilders: TcpSocketBuilder? = null,
        private var isSetSocket: Boolean = false,
        private var address: InetSocketAddress? = null,
        private val standardPort: Int = 2323,
        private val standardHost: String = "127.0.0.1"
    ) {

        private lateinit var clientSocket: Socket

        private val standardInetSocketAddress = InetSocketAddress(standardHost, standardPort)

        fun setExecutor(exec: Executors) = apply { executors = exec }

        fun setSelector(selector: ActorSelectorManager) = apply { selectors = selector }

        fun setTcpBuilder(tcpSocketBuilder: TcpSocketBuilder) = apply { tcpSocketBuilders = tcpSocketBuilder }

        fun setClientSocket(socket: Socket) = apply {
            clientSocket = socket
            isSetSocket = true
        }

        fun setAddress(adr: InetSocketAddress) = apply {
            address = adr
        }

        suspend fun build(): KtorApi {
            if (isSetSocket) {
                return KtorApi(clientSocket)
            }
            if (address != null) {
                selectors?.let {
                    try {
                        clientSocket =
                            address?.let { inetSocketAddress -> getSocket(it, inetSocketAddress) }
                                ?: getSocket(it, standardInetSocketAddress)
                        return KtorApi(clientSocket)
                    } catch (e: java.net.ConnectException) {
                        throw FailConnect("server:$address no response")
                    }
                }
                tcpSocketBuilders?.let {
                    clientSocket =
                        address?.let { inetSocketAddress -> it.connect(inetSocketAddress) } ?: it.connect(
                            standardInetSocketAddress
                        )
                    return KtorApi(clientSocket)
                }
            }
            throw AddressNotSet("address is null")
        }
    }

    private companion object {
        private suspend inline fun getSocket(selectors: ActorSelectorManager, address: InetSocketAddress) =
            aSocket(selectors).tcp().connect(address)
    }
}
