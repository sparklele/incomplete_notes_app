package com.example.notesapp.ui.update_and_delete.view_model_delete

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.AppDatabase
import com.example.notesapp.data.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteNoteViewModel (val app : Application) : AndroidViewModel(app) {
    val _deleteNote: MutableLiveData<Unit> = MutableLiveData()
    val deleteNote : LiveData<Unit> = _deleteNote

    fun deleteNote(note: Note){
        val noteDao = AppDatabase.DatabaseBuilder.getInstance(app.applicationContext).noteDao()
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main) {
                _deleteNote.postValue(noteDao.deleteNote(note))
            }
        }
    }

}