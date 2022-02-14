package com.makentoshe.shuvi.repository

sealed class RepositoryResponse<T: Any> {
    abstract val insertedObject: T

    data class Success<T: Any>(override val insertedObject: T): RepositoryResponse<T>()
}
