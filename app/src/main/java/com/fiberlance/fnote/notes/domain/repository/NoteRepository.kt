package com.fiberlance.fnote.notes.domain.repository

import com.fiberlance.fnote.main.model.BaseDataResponse
import com.fiberlance.fnote.main.model.BaseResponse
import com.fiberlance.fnote.notes.domain.model.Note

interface NoteRepository {
    suspend fun getNotes(): BaseDataResponse<List<Note>>
    suspend fun getNoteById(id: String): BaseDataResponse<Note>
    suspend fun createNote(note: Note): BaseResponse
    suspend fun updateNote(id: String, note: Note): BaseResponse
    suspend fun deleteNote(id: String): BaseResponse
}