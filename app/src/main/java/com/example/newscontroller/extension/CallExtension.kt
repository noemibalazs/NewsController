package com.example.newscontroller.extension

import com.example.newscontroller.core.Either
import com.example.newscontroller.core.ErrorMessage
import com.example.newscontroller.core.Failure
import com.google.gson.JsonParseException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.resume

suspend fun <T> Call<T>.awaitCall(): Either<Failure, T> {
    return suspendCancellableCoroutine { continuation ->
        this.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (continuation.isCancelled) return

                if (response.isSuccessful)
                    continuation.resume(response.body()?.let {
                        Either.Right(it)
                    }
                        ?: run {
                            Either.Left(Failure.ResourceNotFoundFailure("Resource not found failure"))
                        }
                    )
                else
                    continuation.resume( response.errorBody()?.let {
                        val errorBody = it.string()

                        when{

                            response.code() in 400..500 ->{
                                val moshi = Moshi.Builder().build()
                                val jsonAdapter:JsonAdapter<ErrorMessage> = moshi.adapter(ErrorMessage::class.java)
                                val errorResponse:ErrorMessage? = jsonAdapter.fromJson(errorBody)
                                errorResponse?.let {
                                    Either.Left(Failure.ApiErrorFailure(errorBody))
                                }

                            }
                            else -> Either.Left(Failure.NetworkConnectingError("Network connection error"))
                        }


                    }
                        ?: run {
                            Either.Left(Failure.ServerErrorFailure("Server error failure"))
                        })

            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                if (continuation.isCancelled) return

                when (throwable) {
                    is IOException -> {
                        continuation.resume(
                            Either.Left(Failure.NetworkConnectingError("Network connecting error"))
                        )
                    }
                    is JsonParseException -> {
                        continuation.resume(
                            Either.Left(Failure.ResourceParseFailure("Resource parse failure"))
                        )
                    }
                    else -> {
                        continuation.resume(Either.Left(Failure.NetworkConnectingError("Network connecting error")))
                    }
                }
            }

        })
    }
}