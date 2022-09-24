package com.training.a0706012110026_farmapp2.Model

import android.os.Parcel
import android.os.Parcelable
import com.training.a0706012110026_farmapp2.Interface.Food

open class Sapi(override var name: String?, override var age: Int?) : Animal(), Parcelable {
    override fun feed(rumput: Rumput) : String {
        return return "Kamu memberikan makan hewan dengan rerumputan"
    }

    override var type: String?
        get() = "Sapi"
        set(value) {}
    override var imageUri: String? = null
    override fun voice() : String {
        return "Mooooooo"
    }
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sapi> {
        override fun createFromParcel(parcel: Parcel): Sapi {
            return Sapi(parcel)
        }

        override fun newArray(size: Int): Array<Sapi?> {
            return arrayOfNulls(size)
        }
    }


}