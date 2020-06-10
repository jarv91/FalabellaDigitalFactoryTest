package com.falabella.test.presentation.ui.main.model

import android.os.Parcel
import android.os.Parcelable

data class EconomicIndicator(
    var code: String?,
    var name: String?,
    var measurementUnit: String?,
    var date: String?,
    var value: Float
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readFloat()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(code)
        writeString(name)
        writeString(measurementUnit)
        writeString(date)
        writeFloat(value)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<EconomicIndicator> =
            object : Parcelable.Creator<EconomicIndicator> {
                override fun createFromParcel(source: Parcel): EconomicIndicator =
                    EconomicIndicator(source)

                override fun newArray(size: Int): Array<EconomicIndicator?> = arrayOfNulls(size)
            }

        fun fromDomainModel(economicIndicator: com.falabella.test.domain.model.EconomicIndicator): EconomicIndicator =
            EconomicIndicator(
                economicIndicator.code,
                economicIndicator.name,
                economicIndicator.measurementUnit,
                economicIndicator.date,
                economicIndicator.value
            )
    }
}