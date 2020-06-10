package com.falabella.test.domain.model

data class User (
    val id : Long = 0L,
    var name: String,
    var email: String,
    var password: String?
)