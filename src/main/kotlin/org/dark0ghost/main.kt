package org.dark0ghost

import io.ktor.network.selector.ActorSelectorManager
import kotlinx.coroutines.Dispatchers
import org.dark0ghost.eye.Eye
import org.dark0ghost.ktorApi.KtorApi
import java.net.InetAddress
import java.net.InetSocketAddress

private val host: String = InetAddress.getLocalHost()?.toString()?: "127.0.0.1"
private const val port: Int = 600

suspend fun main() {
    val selectorManager = ActorSelectorManager(Dispatchers.IO)
    val address = InetSocketAddress(host,port)
    val apiKtor = KtorApi.Builder().setSelector(selectorManager).setAddress(address).build()
    val eye: Eye = Eye.Builder().setApi(apiKtor).build()
}