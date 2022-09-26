package com.training.a0706012110026_farmapp2.Interface

import com.training.a0706012110026_farmapp2.Model.Animal

interface CardClick {
    fun onCardClick(type : String , animal: Animal)
}