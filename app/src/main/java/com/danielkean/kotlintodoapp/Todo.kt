package com.danielkean.kotlintodoapp

class Todo {
    var title: String? = null
    var description: String? = null
    var dateCreated: String? = null
    @field:JvmField var isCompleted: Boolean? = null

    private constructor() {}
    private constructor(
        title: String,
        description: String,
        dateCreated: String,
        isCompleted: Boolean
    ) {
        this.title = title
        this.description = description
        this.dateCreated = dateCreated
        this.isCompleted = isCompleted
    }
}