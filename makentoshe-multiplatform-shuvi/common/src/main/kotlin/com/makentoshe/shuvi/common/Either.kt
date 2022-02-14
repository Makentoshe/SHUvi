package com.makentoshe.shuvi.common

sealed class Either<out TLeft, out TRight> {

    internal abstract val isLeft: Boolean

    internal abstract val isRight: Boolean

    data class Left<T>(val value: T) : Either<T, Nothing>() {
        override val isLeft = true
        override val isRight = false

        override fun toString(): String = "Either.Left($value)"
    }

    data class Right<T>(val value: T) : Either<Nothing, T>() {
        override val isLeft = false
        override val isRight = true

        override fun toString(): String = "Either.Right($value)"
    }

    inline fun <R> fold(ifLeft: (TLeft) -> R, ifRight: (TRight) -> R): R = when (this) {
        is Right -> ifRight(value)
        is Left -> ifLeft(value)
    }

    inline fun <R> mapLeft(f: (TLeft) -> R): Either<R, TRight> {
        return fold({ Left(f(it)) }, { Right(it) })
    }

    inline fun <R> mapRight(f: (TRight) -> R): Either<TLeft, R> {
        return fold({ Left(it) }, { Right(f(it)) })
    }

    inline fun <RLeft, RRight> bimap(fl: (TLeft) -> RLeft, fr: (TRight) -> RRight): Either<RLeft, RRight> {
        return fold({ Left(fl(it)) }, { Right(fr(it)) })
    }

    inline fun onRight(f: (TRight) -> Unit): Either<TLeft, TRight> {
        if (this is Right) f(this.value)
        return this
    }

    inline fun onLeft(f: (TLeft) -> Unit): Either<TLeft, TRight> {
        if (this is Left) f(this.value)
        return this
    }

    fun isLeft(): Boolean = isLeft

    fun isRight(): Boolean = isRight

}

fun <TLeft> Either<TLeft, Any>.left(): TLeft {
    return (this as Either.Left).value
}

fun <TLeft> Either<TLeft, Any>.leftOrNull(): TLeft? {
    return (this as? Either.Left)?.value
}

fun <TRight> Either<Unit, TRight>.right(): TRight {
    return (this as Either.Right).value
}

fun <TRight> Either<Any, TRight>.rightOrNull(): TRight? {
    return (this as? Either.Right)?.value
}