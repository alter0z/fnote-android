package com.fiberlance.fnote.notes.data.model

import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("noteId") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("note") val note: String?,
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("updatedAt") val updatedAt: String?
)
