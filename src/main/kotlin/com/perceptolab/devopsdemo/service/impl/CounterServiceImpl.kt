package com.perceptolab.devopsdemo.service.impl

import com.perceptolab.devopsdemo.service.CounterService
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class CounterServiceImpl : CounterService {

    object Counter {
        val value = AtomicLong()
    }

    override fun getValue(): Long = Counter.value.get()

    override fun addToCounter(): Long = Counter.value.incrementAndGet()

    override fun removeFromCounter(): Long = Counter.value.decrementAndGet()
}
