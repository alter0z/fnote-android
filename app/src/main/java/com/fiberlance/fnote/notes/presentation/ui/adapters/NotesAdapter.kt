package com.fiberlance.fnote.notes.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fiberlance.fnote.databinding.NoteCardBinding
import com.fiberlance.fnote.notes.domain.model.Note
import com.fiberlance.fnote.main.utils.DateTimeUtil.getTimeAgo
import com.fiberlance.fnote.notes.presentation.ui.listeners.OnNoteClickListener

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: NoteCardBinding): RecyclerView.ViewHolder(binding.root)

    private val notes = ArrayList<Note>()
    private var onNoteClickListener: OnNoteClickListener? = null

    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener
    }

    fun setData(list: List<Note>) {
        this.notes.clear()
        this.notes.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(notes[position]) {
                binding.apply {
                    val mTimeAgo = updatedAt?.let { getTimeAgo(it) } ?: "Unavailable"
                    timeAgo.text = if (mTimeAgo == "0 minutes ago") "Just now" else mTimeAgo
                    title.text = notes[position].title ?: "Unavailable"
                    note.text = notes[position].note ?: "Unavailable"
                    dateAdded.text = notes[position].updatedAt ?: "Unavailable"
                }

                itemView.setOnClickListener { onNoteClickListener?.onNoteClick(this) }
            }
        }
    }
}