package com.perceptolab.devopsdemo.api

import com.perceptolab.devopsdemo.model.api.AddUserRequest
import com.perceptolab.devopsdemo.model.db.User
import com.perceptolab.devopsdemo.repository.UserRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
@RequestMapping("api/db")
class DbController(@Inject private val userRepository: UserRepository) {

    @GetMapping("/users")
    fun getUsers(): List<User> = userRepository.findAll()

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Long): User = userRepository.getById(id)

    @PutMapping("/users")
    fun addUser(@RequestBody addUserRequest: AddUserRequest): User =
        userRepository.save(User.fromRequest(addUserRequest))

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long) = userRepository.deleteById(id)
}
