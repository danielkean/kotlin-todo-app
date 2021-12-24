package com.danielkean.kotlintodoapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firestoreList: RecyclerView
    private lateinit var adapter: FirestoreRecyclerAdapter<Todo, TodoViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseFirestore = FirebaseFirestore.getInstance()
        firestoreList = findViewById(R.id.firestore_list)

        // Query
        val query: Query = firebaseFirestore.collection("todos").orderBy("dateCreated")

        // Options
        val options = FirestoreRecyclerOptions.Builder<Todo>().setQuery(
            query,
            Todo::class.java
        ).build()

        // Adapter
        adapter = object : FirestoreRecyclerAdapter<Todo, TodoViewHolder>(options) {
            override fun onBindViewHolder(holder: TodoViewHolder, i: Int, model: Todo) {
                holder.todoTitle.text = model.title
                holder.todoDescription.text = model.description
                holder.todoDateCreated.text = model.dateCreated
                holder.todoIsCompleted.isChecked = model.isCompleted!!

                holder.todoIsCompleted.setOnClickListener() {
                    val status: Boolean = holder.todoIsCompleted.isChecked
                    firebaseFirestore.collection("todos").document(model.id!!).update("isCompleted", status)
                }

                holder.todoDeleteButton.setOnClickListener() {
                    firebaseFirestore.collection("todos").document(model.id!!).delete()
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.todo_list_item, parent, false)
                return TodoViewHolder(view)
            }
        }

        firestoreList.setHasFixedSize(true)
        firestoreList.layoutManager = LinearLayoutManager(this)
        firestoreList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_add_todo -> openAddTodoScreen()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openAddTodoScreen() {
        val intent =  Intent(this, AddTodoActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}