package com.example.notesapp.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.R
import com.example.notesapp.data.models.Note
import com.example.notesapp.databinding.ItemNoteBinding
import com.example.notesapp.ui.update_and_delete.shared_view.UpdateOrDeleteNoteActivity

class NotesAdapter (val data: List<Note>)
    : ListAdapter<Note, NotesAdapter.MyViewHolder>(UserItemDiffCallback()), NoteClickListener {

    class MyViewHolder(val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        companion object{
            fun from(parent: ViewGroup) : MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNoteBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.note=data.get(position)
        when(position%5){
            0-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.blue
            ))
            1-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.pink
            ))
            2-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.orange
            ))
            3-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.green
            ))
            4-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.lightpink
            ))

        }
        holder.binding.root.setOnClickListener{
            onNoteClicked(data.get(position), holder.binding,position)
        }
    }

    override fun onNoteClicked(note: Note,binding: ItemNoteBinding,id:Int) {
        binding.root.context.startActivity(Intent(binding.root.context, UpdateOrDeleteNoteActivity::class.java).apply { putExtra("note",note) })
    }

}
interface NoteClickListener{
    fun onNoteClicked(note: Note,binding: ItemNoteBinding,id:Int)
}

class UserItemDiffCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title && oldItem.note == newItem.note
    }

}