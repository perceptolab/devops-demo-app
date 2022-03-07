package com.perceptolab.devopsdemo.model.api

data class SystemStatus(
    val hostName: String,
    val hostAddress: String,
    val counter: Long,
)

data class AddUserRequest(
    val name: String,
    val email: String,
    val phone: String,
)
