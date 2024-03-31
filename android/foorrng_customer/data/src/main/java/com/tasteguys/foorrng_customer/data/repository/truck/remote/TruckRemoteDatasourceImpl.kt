package com.tasteguys.foorrng_customer.data.repository.truck.remote

import com.tasteguys.foorrng_customer.data.api.FoodTruckService
import com.tasteguys.foorrng_customer.data.mapper.toData
import com.tasteguys.foorrng_customer.data.mapper.toNonDefault
import com.tasteguys.foorrng_customer.data.model.LocationRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterUpdateResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckDetailResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckFavoriteListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckMarkDto
import com.tasteguys.foorrng_customer.data.model.truck.TruckMarkRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckOperationInfo
import com.tasteguys.foorrng_customer.data.model.truck.TruckOperationInfoDto
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterOperationResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterReviewRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterReviewResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRequest
import com.tasteguys.foorrng_customer.domain.model.truck.TruckOperationData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class TruckRemoteDatasourceImpl @Inject constructor(
    private val truckService: FoodTruckService
) : TruckRemoteDatasource {
    override suspend fun reportFoodTruck(
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateResponse> {
        return if (picture != null) {
            val requestFile = picture.asRequestBody("image/*".toMediaTypeOrNull())
            truckService.reportFoodTruck(
                TruckRequest(
                    name, carNumber, announcement, phoneNumber, category
                ),
                MultipartBody.Part.createFormData("picture", picture.name, requestFile),
            ).toNonDefault()
        } else {
            truckService.reportFoodTruck(
                TruckRequest(
                    name, carNumber, announcement, phoneNumber, category
                ), null,
            ).toNonDefault()
        }

    }

    override suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateResponse> {
        if (picture != null) {
            val requestFile = picture.asRequestBody("image/*".toMediaTypeOrNull())
            return truckService.updateFoodTruck(
                TruckRequest(
                    foodtruckId, name, carNumber, announcement, phoneNumber, category
                ),
                MultipartBody.Part.createFormData("picture", picture.name, requestFile),

                ).toNonDefault()
        } else {
            return truckService.updateFoodTruck(
                TruckRequest(
                    foodtruckId, name, carNumber, announcement, phoneNumber, category
                ), null,

                ).toNonDefault()
        }

    }

    override suspend fun getTruckList(
        latLeft: Double,
        lngLeft: Double,
        latRight: Double,
        lngRight: Double
    ): Result<List<TruckListResponse>> {
        return truckService.getTrucks(
            LocationRequest(latLeft, lngLeft, latRight, lngRight)
        ).toNonDefault()
    }

    override suspend fun getTruckDetail(truckId: Long): Result<TruckDetailResponse> {
        return truckService.getTruckDetail(truckId).toNonDefault()
    }

    override suspend fun markFavoriteTruck(truckId: Long): Result<String> {
        return truckService.markFavoriteFoodTruck(truckId).toNonDefault()
    }

    override suspend fun getFavoriteTruckList(): Result<List<TruckFavoriteListResponse>> {
        return truckService.getFavoriteTrucks().toNonDefault()
    }

    override suspend fun reportFoodTruckOperationInfo(
        truckId: Long,
        address: String,
        lat: Double,
        lng: Double,
        operationInfo: List<TruckOperationInfo>
    ): Result<TruckRegisterOperationResponse> {
        return truckService.registerMark(
            truckId, TruckMarkRequest(
                TruckMarkDto(address, lat, lng),
                TruckOperationInfoDto(operationInfo)
            )
        ).toNonDefault()
    }

    override suspend fun registerReview(
        truckId: Long,
        rvIsDelicious: Boolean,
        rvIsCool: Boolean,
        rvIsClean: Boolean,
        rvIsKind: Boolean,
        rvIsSpecial: Boolean,
        rvIsCheap: Boolean,
        rvIsFast: Boolean
    ): Result<TruckRegisterReviewResponse> {
        return truckService.registerReview(
            truckId, TruckRegisterReviewRequest(
                rvIsDelicious, rvIsCool, rvIsClean, rvIsKind, rvIsSpecial, rvIsCheap, rvIsFast
            )
        ).toNonDefault()
    }
}