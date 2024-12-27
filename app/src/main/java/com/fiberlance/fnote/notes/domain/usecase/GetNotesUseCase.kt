package com.fiberlance.fnote.notes.domain.usecase

import com.fiberlance.fnote.notes.domain.repository.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke() = repository.getNotes()
}