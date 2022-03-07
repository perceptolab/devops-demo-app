package com.perceptolab.devopsdemo.model.api

data class GoRestUser(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val status: String,
)

data class GoRestPost(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
)
