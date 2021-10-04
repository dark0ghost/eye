package org.dark0ghost

import io.ktor.network.selector.*
import kotlinx.coroutines.asCoroutineDispatcher
import org.dark0ghost.enums.NetApi
import org.dark0ghost.exceptions.apiKtorException.FailConnect
import org.dark0ghost.eye.Eye
import org.dark0ghost.factory.ApiFactory
import org.dark0ghost.factory.KtorApiFactory
import org.dark0ghost.ktorApi.KtorApi
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Executors


private const val HOST_ADDRESS: String = "192.168.0.198"
private const val PORT: Int = 4290

fun main() {
    val exec = Executors.newCachedThreadPool()
    val selectorManager = ActorSelectorManager(exec.asCoroutineDispatcher())
    val address = InetSocketAddress(HOST_ADDRESS, PORT)
    val apiKtorBuilder: KtorApi.Builder = KtorApi.Builder().setSelector(selectorManager).setAddress(address)
    val apiFactory = (ApiFactory.getApi(NetApi.KtorApi) as KtorApiFactory).setBuilder(apiKtorBuilder)
    val ktorApi: KtorApi = try {
        apiFactory.createApi() as KtorApi
    }catch (e: FailConnect){
        e.printStackTrace()
        return
    }
    //Eye.Builder().setApi(ktorApi).build()
}