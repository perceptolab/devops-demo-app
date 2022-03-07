package com.perceptolab.devopsdemo.api

import com.perceptolab.devopsdemo.client.GoRestClient
import com.perceptolab.devopsdemo.model.api.GoRestPost
import com.perceptolab.devopsdemo.model.api.GoRestUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
@RequestMapping("api/ext")
class GoRestController(@Inject private val goRestClient: GoRestClient) {

    @GetMapping("users")
    fun getUsers(): List<GoRestUser> {
        return goRestClient.users()
    }

    @GetMapping("posts")
    fun getPosts(): List<GoRestPost> {
        return goRestClient.posts()
    }
}
