package com.perceptolab.devopsdemo.repository

import com.perceptolab.devopsdemo.model.db.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByNameLike(name: String): List<User>
}
