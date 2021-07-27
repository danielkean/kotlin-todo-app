package com.danielkean.kotlintodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val add_todo_button = findViewById<Button>(R.id.add_todo_button)
        add_todo_button.setOnClickListener {
            addTodo()
        }
    }

    private fun addTodo() {
        val titleEditText = findViewById<EditText>(R.id.title_input)
        val titleText = titleEditText.text.toString().trim()
        val descriptionEditText = findViewById<EditText>(R.id.description_input)
        val descriptionText = descriptionEditText.text.toString().trim()
        var valid = true

        if(titleText.isEmpty()) {
            titleEditText.error = "Please enter a title..."
            valid = false
        }

        if(descriptionText.isEmpty()) {
            descriptionEditText.error = "Please enter a description..."
            valid = false
        }

        if(!valid) return

        // Todo can be added to firebase...
        /// ...
        Toast.makeText(this,"Todo task has been added.", Toast.LENGTH_SHORT).show()

        // Go back to main screen
        finish()
    }
}