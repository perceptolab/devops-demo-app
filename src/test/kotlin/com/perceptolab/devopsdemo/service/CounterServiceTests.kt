package com.perceptolab.devopsdemo.service

import com.perceptolab.devopsdemo.service.impl.CounterServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CounterServiceTests {

    private lateinit var counterService: CounterService

    @BeforeEach
    fun setupService() {
        counterService = CounterServiceImpl()
    }

    @Test
    fun testInitial() {
        assertEquals(0L, counterService.getValue())
    }

    @Test
    fun testAdd() {
        counterService.addToCounter()
        assertEquals(1L, counterService.getValue())
    }

    @Test
    fun testRemove() {
        counterService.addToCounter()
        counterService.removeFromCounter()
        assertEquals(0L, counterService.getValue())
    }

    @Test
    fun testShouldNotGoBelowZero() {
        counterService.removeFromCounter()
        assertEquals(0L, counterService.getValue())
    }
}
