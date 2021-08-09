package org.dark0ghost.factory

import org.dark0ghost.api.Api
import org.dark0ghost.enums.NetApi
import org.dark0ghost.exceptions.factoryException.NotFoundApiException
import org.dark0ghost.ktorApi.KtorApi

abstract class ApiFactory {
 abstract fun createApi(): Api

 companion object {
     fun getApi(apiType: NetApi): ApiFactory = when(apiType){
         NetApi.KtorApi -> KtorApiFactory()
         else -> throw NotFoundApiException("api($apiType) not found")
     }
 }
}

internal class KtorApiFactory: ApiFactory() {
    override fun createApi(): Api = KtorApi()

}