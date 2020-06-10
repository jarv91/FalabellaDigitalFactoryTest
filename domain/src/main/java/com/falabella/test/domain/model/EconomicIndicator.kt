package com.falabella.test.domain.model

data class EconomicIndicator(
    var code: String,
    var name: String,
    var measurementUnit: String,
    var date: String,
    var value: Float
)