package com.danielkean.kotlintodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    fun openAddTodoScreen() {

        Toast.makeText(this,"Add Todo Clicked!", Toast.LENGTH_SHORT).show()

        val intent =  Intent(this, AddTodoActivity::class.java)
        startActivity(intent)
    }
}