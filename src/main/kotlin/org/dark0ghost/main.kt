package org.dark0ghost

import io.ktor.network.selector.*
import kotlinx.coroutines.Dispatchers
import org.dark0ghost.enums.NetApi
import org.dark0ghost.factory.ApiFactory
import org.dark0ghost.factory.KtorApiFactory
import org.dark0ghost.ktorApi.KtorApi
import java.net.InetSocketAddress

private const val HOST_ADDRESS: String = "127.0.0.1"
private const val PORT: Int = 6000

suspend fun main() {
    val selectorManager = ActorSelectorManager(Dispatchers.IO)
    val address = InetSocketAddress(HOST_ADDRESS, PORT)
    val apiKtorBuilder: KtorApi.Builder = KtorApi.Builder().setSelector(selectorManager).setAddress(address)
    val apiFactory = (ApiFactory.getApi(NetApi.KtorApi) as KtorApiFactory).setBuilder(apiKtorBuilder)
    //val ktorApi: KtorApi = apiFactory.createApi() as KtorApi
    //ktorApi.getPhoto()
}
// val eye: Eye = Eye.Builder().setApi(apiKtor).build()