package com.sean.p3

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class CovidDataResponse(val totalTestResults: Int, val date: String?, val state: String?, val positive: Int, val death: Int): Serializable,
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalTestResults)
        parcel.writeString(date)
        parcel.writeString(state)
        parcel.writeInt(positive)
        parcel.writeInt(death)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CovidDataResponse> {
        override fun createFromParcel(parcel: Parcel): CovidDataResponse {
            return CovidDataResponse(parcel)
        }

        override fun newArray(size: Int): Array<CovidDataResponse?> {
            return arrayOfNulls(size)
        }
    }

}
