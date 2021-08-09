package org.dark0ghost.factory

import kotlinx.coroutines.runBlocking
import org.dark0ghost.api.Api
import org.dark0ghost.enums.NetApi
import org.dark0ghost.exceptions.factoryException.NotFoundApiException
import org.dark0ghost.ktorApi.KtorApi

abstract class ApiFactory {

    abstract fun createApi(): Api

    companion object {
        fun getApi(apiType: NetApi): ApiFactory = when (apiType) {
            NetApi.KtorApi -> KtorApiFactory()
            else -> throw NotFoundApiException("api($apiType) not found")
        }
    }
}

open class KtorApiFactory : ApiFactory() {
    private lateinit var builder: KtorApi.Builder

    open fun setBuilder(ktorBuilder: KtorApi.Builder): ApiFactory = apply {
        builder = ktorBuilder
    }

    override fun createApi() = runBlocking { builder.build() }
}