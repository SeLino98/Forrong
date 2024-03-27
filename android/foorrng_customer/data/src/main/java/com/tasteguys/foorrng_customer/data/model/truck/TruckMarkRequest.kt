package com.tasteguys.foorrng_customer.data.model.truck

import com.squareup.moshi.Json

data class TruckMarkRequest(
    @Json(name="markDto")
    val markDto: TruckMarkDto,
    @Json(name="operationInfoDto")
    val operationInfo: TruckOperationInfoDto
)
