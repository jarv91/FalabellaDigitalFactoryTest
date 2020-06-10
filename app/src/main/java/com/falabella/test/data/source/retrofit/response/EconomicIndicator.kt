package com.falabella.test.data.source.retrofit.response

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("version")
    val version: String,
    @SerializedName("autor")
    val author: String,
    @SerializedName("fecha")
    val date: String,
    @SerializedName("uf")
    val uf: EconomicIndicator,
    @SerializedName("ivp")
    val ivp: EconomicIndicator,
    @SerializedName("dolar")
    val dollar: EconomicIndicator,
    @SerializedName("dolar_intercambio")
    val exchangeDollar: EconomicIndicator,
    @SerializedName("euro")
    val euro: EconomicIndicator,
    @SerializedName("ipc")
    val ipc: EconomicIndicator,
    @SerializedName("utm")
    val utm: EconomicIndicator,
    @SerializedName("imacec")
    val imacec: EconomicIndicator,
    @SerializedName("tpm")
    val tpm: EconomicIndicator,
    @SerializedName("libra_cobre")
    val copperPound: EconomicIndicator,
    @SerializedName("tasa_desempleo")
    val unemploymentRate: EconomicIndicator,
    @SerializedName("bitcoin")
    val bitcoin: EconomicIndicator
)

data class EconomicIndicator(
    @SerializedName("codigo")
    val code: String,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("unidad_medida")
    val measurementUnit: String,
    @SerializedName("fecha")
    val date: String,
    @SerializedName("valor")
    val value: Float
)