package com.falabella.test.domain.repository

import com.falabella.test.domain.model.EconomicIndicator

interface EconomicIndicatorsRepository {
    suspend fun getAllEconomicIndicators(): List<EconomicIndicator>
}