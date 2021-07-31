package com.danielkean.kotlintodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class AddTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val addTodoButton = findViewById<Button>(R.id.add_todo_button)
        addTodoButton.setOnClickListener {
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
        val db = Firebase.firestore
        val docReference = db.collection("todos").document()

        val todo = hashMapOf(   "id" to docReference.id,
                                "title" to titleText,
                                "description" to descriptionText,
                                "isCompleted" to false,
                                "dateCreated" to Calendar.getInstance().time.toString())

        docReference.set(todo)

        // Toast notification
        Toast.makeText(this,"Todo task has been added.", Toast.LENGTH_SHORT).show()

        // Go back to main screen
        finish()
    }
}