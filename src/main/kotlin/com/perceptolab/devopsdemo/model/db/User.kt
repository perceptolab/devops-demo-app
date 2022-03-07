package com.perceptolab.devopsdemo.model.db

import com.perceptolab.devopsdemo.model.api.AddUserRequest
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "app_user")
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long?,
    val name: String,
    val email: String,
    val phone: String,
) {
    companion object {
        fun fromRequest(request: AddUserRequest): User = User(null, request.name, request.email, request.phone)
    }
}
