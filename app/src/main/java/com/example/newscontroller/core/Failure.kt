package com.example.newscontroller.core

sealed class Failure(message: String) {

    class ApiErrorFailure(message: String): Failure(message)
    class ServerErrorFailure(message: String): Failure(message)
    class ResourceParseFailure(message: String): Failure(message)
    class ResourceNotFoundFailure(message: String): Failure(message)
    class NetworkConnectingError(message: String):Failure(message)
}