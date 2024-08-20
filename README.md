# incomplete_notes_app , < an Assignment>

continuation on @/esraafathy225 's NotesApp repo

**added files :
  com.example.notesapp/update_and_delete/shared_view/UpdateOrDeleteNoteActivity.kt

  com.example.notesapp/update_and_delete/view_model_delete/DeleteNoteViewModel

  com.example.notesapp/update_and_delete/view_model_update/UpdateNoteViewModel

**added to files:

  com.example.notesapp/main/adapter/NotesAdapter.kt
          interface NoteClickListener{}
          function onNoteClicked(note : Note, binding : ItemNoteBinding, id : position){}
          added holder.binding.root.setOnClickListener{} to function onBindViewHolder
          changed implementation of the function areContentsTheSame in class                       UserItemDiffCallback

  res/layout/delete_or_update.xml (majorly original developer's design, I only added an appbar and chnaged the button's text)

  res/menu/delete_bar.xml

**added some values in :

  res/values/colors.xml :
            <color name="darkwhite">#FAFAFA</color>

  res/values/stringsxml:
            <string name="update_note">Update Note</string>,
            <string name="note_updated">Note Updated Successfully</string>,
            <string name="delete">Delete Note</string>,
            <string name="confirm_delete">Do you want to delete this note?</string>,
            <string name="ok">OK</string>,
            <string name="cancel">CANCEL</string>,
            <string name="note_deleted">Note Deleted</string>
            
 --------------------------------------------------------------------------------------------------------------------
