package com.example.notesapp.ui.update_and_delete.view_model_update

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.AppDatabase
import com.example.notesapp.data.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateNoteViewModel (val app : Application) : AndroidViewModel(app) {
    val _updateNote: MutableLiveData<Unit> = MutableLiveData()
    val updateNote: LiveData<Unit> = _updateNote

    fun updateNote(note: Note) {
        val noteDao = AppDatabase.DatabaseBuilder.getInstance(app.applicationContext).noteDao()
        Log.d("here", "entered updateNote in updateNoteViewModel with note title : ${note.title}")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("here", "entered coroutine")
            withContext(Dispatchers.Main) {
                Log.d("here", "entered withContext")
                _updateNote.postValue(noteDao.updateNote(note))
                Log.d("here", "After postValue")
            }
        }
        Log.d("here", "After Coroutine")
    }
}