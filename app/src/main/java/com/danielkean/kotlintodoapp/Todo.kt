package com.danielkean.kotlintodoapp

class Todo {
    var id: String? = null
    var title: String? = null
    var description: String? = null
    var dateCreated: String? = null
    @field:JvmField var isCompleted: Boolean? = null

    private constructor() {}
    private constructor(
        id: String,
        title: String,
        description: String,
        dateCreated: String,
        isCompleted: Boolean
    ) {
        this.id = id
        this.title = title
        this.description = description
        this.dateCreated = dateCreated
        this.isCompleted = isCompleted
    }
}