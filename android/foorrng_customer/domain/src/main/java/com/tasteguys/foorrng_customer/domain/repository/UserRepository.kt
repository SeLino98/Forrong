package com.tasteguys.foorrng_customer.domain.repository

import com.tasteguys.foorrng_customer.domain.model.user.TokenData

interface UserRepository {
    suspend fun registerCustomer(
        userUid: Long,
        name: String,
        email: String
    ): Result<Long>

    suspend fun login(
        userUid: Long,
        name: String,
        email: String
    ): Result<TokenData>
}