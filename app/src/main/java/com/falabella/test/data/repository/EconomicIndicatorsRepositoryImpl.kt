package com.falabella.test.data.repository

import android.content.Context
import com.falabella.test.data.source.retrofit.api.TestApi
import com.falabella.test.data.source.retrofit.network.RetrofitManager
import com.falabella.test.domain.model.EconomicIndicator
import com.falabella.test.domain.repository.EconomicIndicatorsRepository
import com.google.gson.annotations.SerializedName

class EconomicIndicatorsRepositoryImpl(context: Context) : EconomicIndicatorsRepository {
    private val testApi = RetrofitManager().buildService(TestApi::class.java)

    override suspend fun getAllEconomicIndicators(): List<EconomicIndicator> {
        val response =  testApi.getEconomicIndicators()
        val responseList = response.body()
        responseList?.let {
            val list = mutableListOf<EconomicIndicator>()
            list.add(EconomicIndicator(it.uf.code, it.uf.name, it.uf.measurementUnit, it.uf.date, it.uf.value))
            list.add(EconomicIndicator(it.ivp.code, it.ivp.name, it.ivp.measurementUnit, it.ivp.date, it.ivp.value))
            list.add(EconomicIndicator(it.dollar.code, it.dollar.name, it.dollar.measurementUnit, it.dollar.date, it.dollar.value))
            list.add(EconomicIndicator(it.exchangeDollar.code, it.exchangeDollar.name, it.exchangeDollar.measurementUnit, it.exchangeDollar.date, it.exchangeDollar.value))
            list.add(EconomicIndicator(it.euro.code, it.euro.name, it.euro.measurementUnit, it.euro.date, it.euro.value))
            list.add(EconomicIndicator(it.ipc.code, it.ipc.name, it.ipc.measurementUnit, it.ipc.date, it.ipc.value))
            list.add(EconomicIndicator(it.utm.code, it.utm.name, it.utm.measurementUnit, it.utm.date, it.utm.value))
            list.add(EconomicIndicator(it.imacec.code, it.imacec.name, it.imacec.measurementUnit, it.imacec.date, it.imacec.value))
            list.add(EconomicIndicator(it.tpm.code, it.tpm.name, it.tpm.measurementUnit, it.tpm.date, it.tpm.value))
            list.add(EconomicIndicator(it.copperPound.code, it.copperPound.name, it.copperPound.measurementUnit, it.copperPound.date, it.copperPound.value))
            list.add(EconomicIndicator(it.unemploymentRate.code, it.unemploymentRate.name, it.unemploymentRate.measurementUnit, it.unemploymentRate.date, it.unemploymentRate.value))
            list.add(EconomicIndicator(it.bitcoin.code, it.bitcoin.name, it.bitcoin.measurementUnit, it.bitcoin.date, it.bitcoin.value))
            return list
        } ?: run {
            return listOf()
        }
    }

}