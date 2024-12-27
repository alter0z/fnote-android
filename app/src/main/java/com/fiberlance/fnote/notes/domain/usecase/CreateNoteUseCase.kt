package com.fiberlance.fnote.notes.domain.usecase

import com.fiberlance.fnote.notes.domain.model.Note
import com.fiberlance.fnote.notes.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.createNote(note)
}