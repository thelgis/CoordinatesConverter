package com.thelgis.coordinates

fun main(args: Array<String>) {

    val (lat, long, elevation) = CartesianToGCS.convert(args[0].toLong(), args[1].toLong(), args[2].toLong())
    println("Latitude: $lat \n" +
            "Longitude: $long \n" +
            "Elevation: $elevation")

}