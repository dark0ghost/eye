package org.dark0ghost

import io.ktor.network.selector.ActorSelectorManager
import kotlinx.coroutines.Dispatchers
import java.net.InetSocketAddress

suspend fun main() {
    val selectorManager = ActorSelectorManager(Dispatchers.IO)
    val address = InetSocketAddress("127.0.0.1",6000)
    //val apiKtor = KtorApi.Builder().setSelector(selectorManager).setAddress(address).build()
}