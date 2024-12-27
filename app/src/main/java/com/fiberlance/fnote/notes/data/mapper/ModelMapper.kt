package com.fiberlance.fnote.notes.data.mapper

import com.fiberlance.fnote.notes.data.model.Note as DataNote
import com.fiberlance.fnote.notes.domain.model.Note as DomainNote

fun DataNote.toDomainModel(): DomainNote {
    return DomainNote(
        id = this.id,
        title = this.title,
        note = this.note,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun DomainNote.toDataModel(): DataNote {
    return DataNote(
        id = this.id,
        title = this.title,
        note = this.note,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}