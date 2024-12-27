package com.fiberlance.fnote.main.model

import com.google.gson.annotations.SerializedName

data class BaseDataResponse<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("dataSize") val dataSize: Int,
    @SerializedName("data") val data: T
)
