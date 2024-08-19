package com.example.notesapp.ui.update_and_delete.shared_view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.notesapp.R
import com.example.notesapp.data.models.Note
import com.example.notesapp.databinding.DeleteOrUpdateBinding
import com.example.notesapp.ui.update_and_delete.view_model_delete.DeleteNoteViewModel
import com.example.notesapp.ui.update_and_delete.view_model_update.UpdateNoteViewModel
import com.google.android.material.snackbar.Snackbar

class UpdateOrDeleteNoteActivity : AppCompatActivity() {
    lateinit var note: Note
    private val viewModelupdate: UpdateNoteViewModel by viewModels()
    private val viewModeldelete: DeleteNoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DeleteOrUpdateBinding.inflate(layoutInflater)
        note = intent.getSerializableExtra("note") as Note
        val id = note.id
        binding.note = note
        setContentView(binding.root)
        binding.appbar.outlineProvider = null
        binding.appbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.trash -> {
                    Log.d("trash", "entered onOptionsItemSelected, so clicked the trash icon!")
                    Log.d("trash", "entered when condition, so it catched the id of trash icon!")

                    val dialogBuilder = AlertDialog.Builder(this)
                    Log.d("trash", "created the dialogBuilder")
                    dialogBuilder.setMessage(getString(R.string.confirm_delete))
                        .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                            viewModeldelete.deleteNote(note)
                            dialog.dismiss()
                            Toast.makeText(
                                this,
                                getString(R.string.note_deleted),
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                        .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                            dialog.dismiss()
                        }
                    val dialog = dialogBuilder.create()
                    dialog.show()
                    Log.d("trash", "finished the dialog code and about to return true")
                    true
                }

                else -> false
            }
        }


        //update content

        binding.btUpdate.setOnClickListener {
            if (!binding.etTitle.text.isNullOrEmpty() &&
                !binding.etNote.text.isNullOrEmpty()
            ) {
                Log.d("here", "entered if condition ")
                viewModelupdate.updateNote(
                    Note(
                        id, binding.etTitle.text.toString(),
                        binding.etNote.text.toString()
                    )
                )
                Log.d("here", "after viewmodel.updateNote with id : $id")
            } else
                Log.d("here", "entered button listener but not if condition ")
        }

        viewModelupdate.updateNote.observe(this, Observer {
            binding.btUpdate.hideKeyboard()
            Snackbar.make(binding.main, R.string.note_updated, Snackbar.LENGTH_LONG)
                .setAction(R.string.dismiss) {
                    finish()
                }.show()
        })


    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    //delete content

}