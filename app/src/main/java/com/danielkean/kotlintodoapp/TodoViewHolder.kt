package com.danielkean.kotlintodoapp

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var todoTitle: TextView = itemView.findViewById(R.id.todo_title)
    var todoDescription: TextView = itemView.findViewById(R.id.todo_description)
    var todoDateCreated: TextView = itemView.findViewById(R.id.todo_date_created)
    var todoIsCompleted: CheckBox = itemView.findViewById(R.id.is_complete_checkbox)
}