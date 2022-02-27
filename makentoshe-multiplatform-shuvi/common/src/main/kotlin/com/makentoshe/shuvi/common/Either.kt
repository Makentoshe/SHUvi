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

fun <TLeft, TRight> Either<TLeft, TRight>.filterLeft2Right(right: TRight, f: (TLeft) -> Boolean) = when (this) {
    is Either.Right -> this
    is Either.Left -> if (f(value)) Either.Left(value) else Either.Right(right)
}

fun <TRight> Either<Any, TRight>.right(): TRight {
    return (this as Either.Right).value
}

fun <TRight> Either<Any, TRight>.rightOrNull(): TRight? {
    return (this as? Either.Right)?.value
}

inline fun <TLeft, TRight, RRight> Either<TLeft, TRight>.flatMapRight(
    f: (TRight) -> Either<TLeft, RRight>,
): Either<TLeft, RRight> = when (this) {
    is Either.Right -> f(this.value)
    is Either.Left -> this
}

inline fun <TLeft, TRight, RLeft> Either<TLeft, TRight>.flatMapLeft(
    f: (TLeft) -> Either<RLeft, TRight>,
): Either<RLeft, TRight> = when (this) {
    is Either.Right -> this
    is Either.Left -> f(this.value)
}

/** Converts List<Either<T, *> to Either<List<T>, *> */
fun <TLeft, TRight> List<Either<TLeft, TRight>>.flattenLeft(): Either<List<TLeft>, TRight> {
    val list = ArrayList<TLeft>()
    forEach { either -> either.onRight { return Either.Right(it) }.onLeft { list.add(it) } }
    return Either.Left(list)
}

fun <TLeft1, TLeft2, RLeft, TRight> Either<TLeft1, TRight>.andOtherLeft(
    other: Either<TLeft2, TRight>,
    f: (TLeft1, TLeft2) -> RLeft,
) = flatMapLeft { first -> other.mapLeft { second -> f(first, second) } }
