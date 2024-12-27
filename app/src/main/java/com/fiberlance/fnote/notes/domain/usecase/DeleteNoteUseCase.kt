package com.fiberlance.fnote.notes.domain.usecase

import com.fiberlance.fnote.notes.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: String) = repository.deleteNote(id)
}