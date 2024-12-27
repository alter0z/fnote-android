package com.fiberlance.fnote.notes.data.repository

import com.fiberlance.fnote.main.model.BaseDataResponse
import com.fiberlance.fnote.notes.data.api.NoteApi
import com.fiberlance.fnote.notes.data.mapper.toDataModel
import com.fiberlance.fnote.notes.data.mapper.toDomainModel
import com.fiberlance.fnote.notes.domain.model.Note
import com.fiberlance.fnote.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val api: NoteApi
) : NoteRepository {
    override suspend fun getNotes(): BaseDataResponse<List<Note>> {
        val response = api.getNotes()
        return BaseDataResponse(
            status = response.status,
            message = response.message,
            dataSize = response.dataSize,
            data = response.data.map { it.toDomainModel() }
        )
    }

    override suspend fun getNoteById(id: String): BaseDataResponse<Note> {
        val response = api.getNoteById(id)
        return BaseDataResponse(
            status = response.status,
            message = response.message,
            dataSize = response.dataSize,
            data = response.data.toDomainModel()
        )
    }

    override suspend fun createNote(note: Note) = api.createNote(note.toDataModel())

    override suspend fun updateNote(id: String, note: Note) = api.updateNote(id, note.toDataModel())

    override suspend fun deleteNote(id: String) = api.deleteNote(id)
}