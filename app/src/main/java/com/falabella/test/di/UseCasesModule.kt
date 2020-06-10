package com.falabella.test.di

import com.falabella.test.domain.repository.AuthRepository
import com.falabella.test.domain.repository.EconomicIndicatorsRepository
import com.falabella.test.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun providesLoginUseCase(authRepository: AuthRepository) = LoginUseCase(authRepository)

    @Provides
    fun providesRestartUserUseCase(authRepository: AuthRepository) = RestartUserUseCase(authRepository)

    @Provides
    fun providesGetIndicatorsUseCase(economicIndicatorsRepository: EconomicIndicatorsRepository) = GetIndicatorsUseCase(economicIndicatorsRepository)

    @Provides
    fun providesCheckSessionUseCase(authRepository: AuthRepository) = CheckSessionUseCase(authRepository)

    @Provides
    fun providesLogoutUseCase(authRepository: AuthRepository) = LogoutUseCase(authRepository)
}