package com.perceptolab.devopsdemo.client

import com.perceptolab.devopsdemo.model.api.GoRestPost
import com.perceptolab.devopsdemo.model.api.GoRestUser
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "gorest", url = "https://gorest.co.in", path = "/public/v2")
interface GoRestClient {

    @GetMapping("/users")
    fun users(): List<GoRestUser>

    @GetMapping("/posts")
    fun posts(): List<GoRestPost>

}
