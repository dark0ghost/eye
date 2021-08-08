package org.dark0ghost

import io.ktor.network.selector.ActorSelectorManager
import kotlinx.coroutines.Dispatchers
import org.dark0ghost.ktorApi.KtorApi
import java.net.InetSocketAddress

private const val host: String = "127.0.0.1"
private const val PORT: Int = 6000

suspend fun main() {
    val selectorManager = ActorSelectorManager(Dispatchers.IO)
    val address = InetSocketAddress(host,PORT)
    println(address)
    val apiKtor = KtorApi.Builder().setSelector(selectorManager).setAddress(address).build()
   // val eye: Eye = Eye.Builder().setApi(apiKtor).build()
}