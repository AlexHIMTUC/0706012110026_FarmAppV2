package com.training.a0706012110026_farmapp2.Model

import android.os.Parcel
import android.os.Parcelable
import com.training.a0706012110026_farmapp2.Interface.Food

open class Ayam(override var name: String?, override var age: Int?): Animal(), Parcelable {

    override var type: String?
        get() = "Ayam"
        set(value) {}
    override var imageUri: String? = null

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun feed(bijiBijian: BijiBijian) : String{
        return "Aku makan biji gaisssws"
    }

    override fun voice() : String {
        return "Kukuruyukkkkkk"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ayam> {
        override fun createFromParcel(parcel: Parcel): Ayam {
            return Ayam(parcel)
        }

        override fun newArray(size: Int): Array<Ayam?> {
            return arrayOfNulls(size)
        }
    }
}