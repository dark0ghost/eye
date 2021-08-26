package org.dark0ghost

import io.ktor.network.selector.ActorSelectorManager
import kotlinx.coroutines.asCoroutineDispatcher
import org.dark0ghost.enums.NetApi
import org.dark0ghost.factory.ApiFactory
import org.dark0ghost.factory.KtorApiFactory
import org.dark0ghost.ktorApi.KtorApi
import java.net.InetSocketAddress
import java.util.concurrent.Executors

private const val HOST_ADDRESS: String = "192.168.0.197"
private const val PORT: Int = 4290

suspend fun main() {
    val exec = Executors.newCachedThreadPool()
    val selectorManager = ActorSelectorManager(exec.asCoroutineDispatcher())
    val address = InetSocketAddress(HOST_ADDRESS, PORT)
    val apiKtorBuilder: KtorApi.Builder = KtorApi.Builder().setSelector(selectorManager).setAddress(address)
    val apiFactory = (ApiFactory.getApi(NetApi.KtorApi) as KtorApiFactory).setBuilder(apiKtorBuilder)
    val ktorApi: KtorApi = apiFactory.createApi() as KtorApi
    val res = ktorApi.getPhoto()
    println(res)
    // val eye: Eye = Eye.Builder().setApi(apiKtor).build()
}