package com.makentoshe.shuvi.service

import com.makentoshe.shuvi.common.Either
import com.makentoshe.shuvi.common.flatMapLeft
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.response.respond
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class ServiceCall(private val applicationCall: ApplicationCall) {

    val parameters: Parameters
        get() = applicationCall.parameters

    val queries: Parameters
        get() = applicationCall.request.queryParameters

    suspend fun <T : Any> respond(code: Int, value: T) = respond(HttpStatusCode.fromValue(code), value)

    suspend fun <T : Any> respond(code: HttpStatusCode, value: T) {
        applicationCall.respond<Any>(code, value)
    }

    suspend fun <T : Any> receive(type: KType): Either<T, Exception> = try {
        Either.Left(applicationCall.receive(type))
    } catch (exception: Exception) {
        Either.Right(exception)
    }

    suspend fun receiveString(): Either<String, Exception> = try {
        Either.Left(applicationCall.receiveText())
    } catch (exception: Exception) {
        Either.Right(exception)
    }
}

suspend inline fun <reified T : Any> ServiceCall.receive(): Either<T, Exception> = receive(typeOf<T>())

suspend inline fun ServiceCall.receiveInt(): Either<Int, Exception> = receiveString().flatMapLeft { string ->
    try {
        Either.Left(string.toInt())
    } catch (nfe: java.lang.NumberFormatException) {
        Either.Right(nfe)
    }
}

fun ServiceCall.parameter(name: String): Either<String, Exception> {
    val value = parameters[name]
    return if (value == null) Either.Right(Exception("$name parameter issue")) else Either.Left(value)
}
