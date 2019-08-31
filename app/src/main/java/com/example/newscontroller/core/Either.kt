package com.example.newscontroller.core

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L): Either<L, Nothing>()
    data class Right<out R>(val b: R): Either<Nothing, R>()

    val isRight: Boolean
    get() = this is Right<R>

    val isLeft: Boolean
    get() = this is Left<L>

    inline fun <T> either(fnL: (L) -> T, fnR: (R) -> T): T{
        return when(this){
            is Left<L> -> fnL(a)
            is Right<R> -> fnR(b)
        }
    }
}