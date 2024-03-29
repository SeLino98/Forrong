package com.tasteguys.foorrng_customer.data.mapper

import com.tasteguys.foorrng_customer.data.model.truck.TruckDetailMarkResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckDetailResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckFavoriteListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckMainInfoResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckMenuResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckOperationInfo
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterOperationResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterUpdateResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckReviewResponse
import com.tasteguys.foorrng_customer.data.model.user.LoginResponse
import com.tasteguys.foorrng_customer.domain.model.truck.FavoriteTruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailMarkData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckMainData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckMenuData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckOperationData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckRegisterUpdateData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckReviewData
import com.tasteguys.foorrng_customer.domain.model.user.TokenData

fun LoginResponse.toDomain() = TokenData(
    accessToken = accessToken,
    refreshToken = refreshToken
)

fun TruckListResponse.toDomain() = TruckData(
    truckId = truckId,
    markId = markId,
    latitude = latitude,
    longitude = longitude,
    name = name,
    picture = picture,
    type = type,
    category = category,
    reviewCnt = reviewCnt,
    favorite = favorite,
    isOperating = isOperating
)

fun TruckDetailResponse.toDomain() = TruckDetailData(
    type,
    mainInfo.toDomain(),
    reviews.map { it.toDomain() },
    menus.map { it.toDomain() },
    totalReview,
    operation.map { it.toDomain() }
)

fun TruckMainInfoResponse.toDomain() = TruckMainData(
    announcement, name, createdDay, picture, accountInfo, carNumber, phoneNumber, bussiNumber?:"", category
)

fun TruckReviewResponse.toDomain() = TruckReviewData(
    id, cnt
)

fun TruckMenuResponse.toDomain() = TruckMenuData(
    id, name, price, picture
)

fun TruckOperationInfo.toDomain() = TruckOperationData(
    day, startTime, endTime
)

fun TruckFavoriteListResponse.toDomain() = FavoriteTruckData(
    id, name, picture, category, reviewCnt
)

fun TruckRegisterUpdateResponse.toDomain() = TruckRegisterUpdateData(
    id, announcement, createdDay, picture, name, accountInfo, carNumber, phoneNumber, category
)

fun TruckDetailMarkResponse.toDomain() = TruckDetailMarkData(
    id, lat, lng, address, isOpen, operationInfoList.map { it.toDomain() }
)

fun TruckRegisterOperationResponse.toDomain() = TruckDetailMarkData(
    id, lat, lng, "", false, operationInfo.map { it.toDomain() }
)

