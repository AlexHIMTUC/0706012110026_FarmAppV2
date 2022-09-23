package com.training.a0706012110026_farmapp2.Model

open class Animal {
    open var name : String? = null
    open var age : Int? = null
    open var imageUri : String? = null
    open var type : String? = null
    open fun feed(rumput: Rumput) : String{
        return ""
    }

    open fun feed(bijiBijian: BijiBijian) : String{
        return ""
    }

    open fun voice() : String{
        return ""
    }
}