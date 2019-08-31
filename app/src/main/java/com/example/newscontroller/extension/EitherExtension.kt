package com.example.newscontroller.extension

import com.example.newscontroller.core.Either

inline fun<T, L, R> Either<L, R>.map(fn: (R) -> T): Either<L, T >{
    return when(this){
        is Either.Left -> Either.Left(this.a)
        is Either.Right -> Either.Right(fn(this.b))
    }
}