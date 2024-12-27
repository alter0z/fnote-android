package com.fiberlance.fnote.notes.presentation.ui.listeners

import com.fiberlance.fnote.notes.domain.model.Note

interface OnNoteClickListener {
    fun onNoteClick(note: Note)
}