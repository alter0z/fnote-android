package com.fiberlance.fnote.notes.data.api

import com.fiberlance.fnote.main.model.BaseDataResponse
import com.fiberlance.fnote.main.model.BaseResponse
import com.fiberlance.fnote.notes.data.model.Note
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface NoteApi {
    @GET("/note")
    suspend fun getNotes(): BaseDataResponse<List<Note>>

    @GET("/note")
    suspend fun getNoteById(@Query("id") id: String): BaseDataResponse<Note>

    @POST("/note")
    suspend fun createNote(@Body note: Note): BaseResponse

    @PUT("/note")
    suspend fun updateNote(@Query("id") id: String, @Body note: Note): BaseResponse

    @DELETE("/note")
    suspend fun deleteNote(@Query("id") id: String): BaseResponse
}