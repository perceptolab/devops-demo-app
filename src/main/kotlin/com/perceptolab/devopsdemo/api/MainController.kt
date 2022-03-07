package com.perceptolab.devopsdemo.api

import com.perceptolab.devopsdemo.model.api.SystemStatus
import com.perceptolab.devopsdemo.service.CounterService
import org.springframework.web.bind.annotation.*
import java.net.InetAddress
import javax.inject.Inject

@RestController
@RequestMapping("api")
class MainController(@Inject private val counterService: CounterService) {

    @PutMapping("counter")
    fun incrementCounter(): Long = counterService.addToCounter()

    @DeleteMapping("counter")
    fun decrementCounter(): Long = counterService.removeFromCounter()

    @GetMapping("status")
    fun getSystemStatus(): SystemStatus {
        return SystemStatus(
            InetAddress.getLocalHost().hostName,
            InetAddress.getLocalHost().hostAddress,
            counterService.getValue()
        )
    }
}
