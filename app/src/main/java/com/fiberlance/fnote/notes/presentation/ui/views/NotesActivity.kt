package com.fiberlance.fnote.notes.presentation.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fiberlance.fnote.databinding.ActivityNotesBinding
import com.fiberlance.fnote.main.model.UiState
import com.fiberlance.fnote.main.utils.AlertDialog
import com.fiberlance.fnote.notes.domain.model.Note
import com.fiberlance.fnote.notes.presentation.ui.adapters.LoadingNotesAdapter
import com.fiberlance.fnote.notes.presentation.ui.adapters.NotesAdapter
import com.fiberlance.fnote.notes.presentation.ui.listeners.OnNoteClickListener
import com.fiberlance.fnote.notes.presentation.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {
    private var _binding: ActivityNotesBinding? = null
    private val binding get() = _binding
    private val viewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.main?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.fetchNotes()
            }
        }

        viewModel.fetchNotes()

        val adapter = NotesAdapter()
        val loadingAdapter = LoadingNotesAdapter()
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val alert = AlertDialog(this)

        binding?.apply {
            add.setOnClickListener {
                activityResultLauncher.launch(
                    Intent(this@NotesActivity, WriteNoteActivity::class.java)
                        .putExtra("create", true)
                )
            }
            lifecycleScope.launch {
                viewModel.notesState.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            notes.adapter = loadingAdapter
                            notes.layoutManager = layoutManager
                        }
                        is UiState.Success -> {
                            adapter.setData(uiState.data.data)
                            notes.adapter = adapter
                            notes.layoutManager = layoutManager
                        }
                        is UiState.Error -> alert.errorAlert(uiState.message)
                    }
                }
            }
        }

        adapter.setOnNoteClickListener(object: OnNoteClickListener{
            override fun onNoteClick(note: Note) {
                activityResultLauncher.launch(
                    Intent(this@NotesActivity, WriteNoteActivity::class.java)
                        .putExtra("id", note.id)
                        .putExtra("title", note.title)
                        .putExtra("note", note.note)
                        .putExtra("createdAt", note.createdAt)
                )
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}