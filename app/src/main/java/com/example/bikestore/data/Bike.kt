package com.example.bikestore.data

import java.util.Date

data class Bike(
    val bikeName:String,
    val bikeImage:Int,
    val bikeDescription:String,
    val bikeStars:Float,
    val borrowRatePerDay:Int,
    val features:BikeFeatures,
    var isBorrowed:Boolean,
    var borrowedDate: String
)
