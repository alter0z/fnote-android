package com.fiberlance.fnote.notes.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fiberlance.fnote.databinding.LoadingNoteCardBinding

class LoadingNotesAdapter: RecyclerView.Adapter<LoadingNotesAdapter.ViewHolder>() {
    inner class ViewHolder(binding: LoadingNoteCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LoadingNoteCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}
}