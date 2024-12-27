package com.fiberlance.fnote.notes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiberlance.fnote.main.model.BaseDataResponse
import com.fiberlance.fnote.main.model.BaseResponse
import com.fiberlance.fnote.main.model.UiState
import com.fiberlance.fnote.notes.domain.model.Note
import com.fiberlance.fnote.notes.domain.usecase.CreateNoteUseCase
import com.fiberlance.fnote.notes.domain.usecase.DeleteNoteUseCase
import com.fiberlance.fnote.notes.domain.usecase.GetNoteByIdUseCase
import com.fiberlance.fnote.notes.domain.usecase.GetNotesUseCase
import com.fiberlance.fnote.notes.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _notesState = MutableStateFlow<UiState<BaseDataResponse<List<Note>>>>(UiState.Loading)
    val notesState: StateFlow<UiState<BaseDataResponse<List<Note>>>> get() = _notesState

    private val _noteState = MutableStateFlow<UiState<BaseDataResponse<Note>>>(UiState.Loading)
    val noteState: StateFlow<UiState<BaseDataResponse<Note>>> get() = _noteState

    private val _state = MutableStateFlow<UiState<BaseResponse>>(UiState.Loading)
    val state: StateFlow<UiState<BaseResponse>> get() = _state

    fun fetchNotes() {
        viewModelScope.launch {
            _notesState.value = UiState.Loading
            try {
                val response = getNotesUseCase()
                _notesState.value = UiState.Success(response)
            } catch (e: HttpException) {
                val errorMessage = try {
                    e.response()?.errorBody()?.string()?.let {
                        JSONObject(it).get("message")
                    }
                } catch (e: JSONException) {
                    e.localizedMessage
                } as String
                _notesState.value = UiState.Error(errorMessage)
            }
        }
    }

    fun fetchNoteById(id: String) {
        viewModelScope.launch {
            _noteState.value = UiState.Loading
            try {
                val response = getNoteByIdUseCase(id)
                _noteState.value = UiState.Success(response)
            } catch (e: HttpException) {
                val errorMessage = try {
                    e.response()?.errorBody()?.string()?.let {
                        JSONObject(it).get("message")
                    }
                } catch (e: JSONException) {
                    e.localizedMessage
                } as String
                _noteState.value = UiState.Error(errorMessage)
            }
        }
    }

    fun createNote(note: Note) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val response = createNoteUseCase(note)
                _state.value = UiState.Success(response)
            } catch (e: HttpException) {
                val errorMessage = try {
                    e.response()?.errorBody()?.string()?.let {
                        JSONObject(it).get("message")
                    }
                } catch (e: JSONException) {
                    e.localizedMessage
                } as String
                _state.value = UiState.Error(errorMessage)
            }
        }
    }

    fun updateNote(id:String, note: Note) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val response = updateNoteUseCase(id, note)
                _state.value = UiState.Success(response)
            } catch (e: HttpException) {
                val errorMessage = try {
                    e.response()?.errorBody()?.string()?.let {
                        JSONObject(it).get("message")
                    }
                } catch (e: JSONException) {
                    e.localizedMessage
                } as String
                _state.value = UiState.Error(errorMessage)
            }
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val response = deleteNoteUseCase(id)
                _state.value = UiState.Success(response)
            } catch (e: HttpException) {
                val errorMessage = try {
                    e.response()?.errorBody()?.string()?.let {
                        JSONObject(it).get("message")
                    }
                } catch (e: JSONException) {
                    e.localizedMessage
                } as String
                _state.value = UiState.Error(errorMessage)
            }
        }
    }
}