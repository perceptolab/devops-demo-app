package com.perceptolab.devopsdemo.api

import com.perceptolab.devopsdemo.model.api.AddUserRequest
import com.perceptolab.devopsdemo.model.db.User
import com.perceptolab.devopsdemo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.inject.Inject

@RestController
@RequestMapping("api/db")
class DbController(@Inject private val userRepository: UserRepository) {

    @GetMapping("/users")
    fun getUsers(): List<User> = userRepository.findAll()

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Long): User = userRepository.findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User $id not found") }

    @PutMapping("/users")
    fun addUser(@RequestBody addUserRequest: AddUserRequest): User =
        userRepository.save(User.fromRequest(addUserRequest))

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        if (userRepository.findById(id).isPresent) {
            userRepository.deleteById(id)
        }
        return ResponseEntity.ok().build()
    }
}
