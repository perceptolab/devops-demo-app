package com.perceptolab.devopsdemo.service

interface CounterService {
    fun getValue(): Long
    fun addToCounter(): Long
    fun removeFromCounter(): Long
}
