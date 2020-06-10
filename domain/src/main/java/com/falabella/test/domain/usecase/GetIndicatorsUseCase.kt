package com.falabella.test.domain.usecase

import com.falabella.test.domain.model.EconomicIndicator
import com.falabella.test.domain.repository.EconomicIndicatorsRepository

class GetIndicatorsUseCase(private val economicIndicatorsRepository: EconomicIndicatorsRepository): BaseUseCase<None, List<EconomicIndicator>>() {
    override suspend fun invoke(params: None): List<EconomicIndicator> = economicIndicatorsRepository.getAllEconomicIndicators()
}