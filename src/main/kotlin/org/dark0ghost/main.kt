package org.dark0ghost
import io.ktor.network.selector.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import org.dark0ghost.ktorApi.KtorApi
import java.net.InetSocketAddress

infix fun Int.`+-`(f: Int): Int{
    return 1;
}

@OptIn(KtorExperimentalAPI::class)
suspend fun main() {
    //val selectorManager = ActorSelectorManager(Dispatchers.IO)
    //val address = InetSocketAddress("127.0.0.1",6000)
   // val apiKtor = KtorApi.Builder().setSelector(selectorManager).setAddress(address).build()
    val f = 1 `+-` 2
    println(f)
}