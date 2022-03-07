package com.perceptolab.devopsdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class DevopsdemoApplication

fun main(args: Array<String>) {
    runApplication<DevopsdemoApplication>(*args)
}
