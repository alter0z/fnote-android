package com.fiberlance.fnote.notes.presentation.ui.views

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.fiberlance.fnote.databinding.ActivityWriteNoteBinding
import com.fiberlance.fnote.main.model.UiState
import com.fiberlance.fnote.main.utils.AlertDialog
import com.fiberlance.fnote.notes.domain.model.Note
import com.fiberlance.fnote.notes.presentation.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WriteNoteActivity : AppCompatActivity() {
    private var _binding: ActivityWriteNoteBinding? = null
    private val binding get() = _binding
    private val viewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityWriteNoteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.main?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val isCreate = intent.getBooleanExtra("create", false)
        val id = intent.getStringExtra("id")
        val titleString = intent.getStringExtra("title")
        val noteString = intent.getStringExtra("note")
        val createdAt = intent.getStringExtra("createdAt")
        val alert = AlertDialog(this)

        binding?.apply {
            cancel.setOnClickListener {
                finish()
                title.text?.clear()
                note.text?.clear()
            }

            if (!isCreate) {
                title.setText(titleString ?: "")
                note.setText(noteString ?: "")
            }

            save.setOnClickListener {
                if (isCreate)
                    viewModel.createNote(Note("", title.text?.toString(), note.text?.toString(), "", ""))
                else
                    viewModel.updateNote(id ?: "-", Note("", title.text?.toString(), note.text?.toString(), createdAt ?: "", ""))
                lifecycleScope.launch {
                    viewModel.state.collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                title.isEnabled = false
                                note.isEnabled = false
                                cancel.visibility = View.GONE
                                save.visibility = View.GONE
                            }
                            is UiState.Success -> {
                                title.isEnabled = true
                                note.isEnabled = true
                                cancel.visibility = View.VISIBLE
                                save.visibility = View.VISIBLE
                                alert.successWithCallback(uiState.data.message) {
                                    setResult(RESULT_OK, intent)
                                    finish()
                                    title.text?.clear()
                                    note.text?.clear()
                                }
                            }
                            is UiState.Error -> {
                                title.isEnabled = true
                                note.isEnabled = true
                                cancel.visibility = View.VISIBLE
                                save.visibility = View.VISIBLE
                                alert.errorAlert(uiState.message)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}