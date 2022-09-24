package com.training.a0706012110026_farmapp2.Model

import android.os.Parcel
import android.os.Parcelable
import com.training.a0706012110026_farmapp2.Interface.Food

open class Kambing(override var name: String?, override var age: Int?): Animal(), Parcelable {

    override var imageUri: String? = null
    override var type: String?
        get() = "Kambing"
        set(value) {}

    override fun feed(rumput: Rumput) : String{
        return "Kamu memberikan makan hewan dengan rerumputan"
    }

    override fun voice() : String {
        return "BLEHHHH"
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

    companion object CREATOR : Parcelable.Creator<Kambing> {
        override fun createFromParcel(parcel: Parcel): Kambing {
            return Kambing(parcel)
        }

        override fun newArray(size: Int): Array<Kambing?> {
            return arrayOfNulls(size)
        }
    }
}