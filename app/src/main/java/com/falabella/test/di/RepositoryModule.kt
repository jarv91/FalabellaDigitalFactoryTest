package com.falabella.test.di

import android.app.Application
import com.falabella.test.data.repository.AuthRepositoryImpl
import com.falabella.test.data.repository.EconomicIndicatorsRepositoryImpl
import com.falabella.test.domain.repository.AuthRepository
import com.falabella.test.domain.repository.EconomicIndicatorsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideAuthRepository(app: Application): AuthRepository = AuthRepositoryImpl(app)

    @Provides
    fun provideEconomicIndicatorsRepository(app: Application): EconomicIndicatorsRepository = EconomicIndicatorsRepositoryImpl(app)
}