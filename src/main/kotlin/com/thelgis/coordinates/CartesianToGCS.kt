package com.thelgis.coordinates

import org.apache.commons.math3.analysis.function.Atan
import org.apache.commons.math3.analysis.function.Divide


object CartesianToGCS {

    private const val degree180           = 3.14159           // Unit: radian
    private const val a                   = 6378137.0         // Unit: meters
    private const val b                   = 6356752.3142      // Unit: meters
    private const val aDividedByb         = a / b
    private const val bDividedBya         = b / a
    private const val eSquared            = 0.00669437999014
    private const val e2Squared           = 0.00673949674228
    private const val tanUConvergeValue   = 0.1

    private val arcTangent = Atan()
    private val divide = Divide()


    /**
     * Converts a set of Cartesian coordinates to the Geographic Coordinate System WGS84.
     *
     * @param x of Cartesian coordinates
     * @param y of Cartesian coordinates
     * @param z of Cartesian coordinates
     *
     * @return GCS latitude, longitude, elevation
     */
    fun convert(x: Long, y: Long, z: Long): GCS {

        val longitude = calculateLongitude(x, y)
        val longitudeDegrees = Math.toDegrees(longitude)

        val p = Math.sqrt((x * x + y * y).toDouble())

        val latitude = calculateLatitude(p, z)
        val latitudeDegrees = Math.toDegrees(latitude)

        val elevation = calculateElevation(p, z.toDouble(), latitude)

        return GCS(latitudeDegrees, longitudeDegrees, elevation)
    }


    /**
     * @param x of Cartesian coordinates
     * @param y of Cartesian coordinates
     *
     * @return longitude
     */
    private fun calculateLongitude(x: Long, y: Long): Double {

        if (x == 0L) throw IllegalArgumentException("Cartesian X cannot be zero")

        var arctanValue = arcTangent.value(divide.value(y.toDouble(), x.toDouble()))

        if (x < 0) arctanValue += if (y >= 0) degree180 else -degree180

        return arctanValue
    }


    /**
     * @param p
     * @param z of Cartesian coordinates
     *
     * @return latitude
     */
    private fun calculateLatitude(p: Double, z: Long): Double {
        val tanU = divide.value(z.toDouble(), p) * aDividedByb
        return arcTangent.value(tanUConverge(p, tanU, z))
    }


    /**
     *
     * @param p
     * @param tanU
     * @param z of Cartesian coordinates
     *
     */
    private fun tanUConverge(p: Double, tanU: Double, z: Long): Double {
        val tanUSquared = tanU * tanU
        val cosUSquared = divide.value(1.0, 1 + tanUSquared)
        val sinUSquared = 1 - cosUSquared
        val tanF = divide.value(z + e2Squared * b * sinUSquared * Math.sqrt(sinUSquared),
                                p - eSquared * a * cosUSquared * Math.sqrt(cosUSquared))

        val newTanU = bDividedBya * tanF

        // If tanU converged return it, otherwise make a recursive call
        return if (Math.abs(newTanU - tanU) < tanUConvergeValue) newTanU else tanUConverge(p, newTanU, z)
    }


    /**
     * @param p
     * @param z
     * @param latitude
     *
     * @return
     */
    private fun calculateElevation(p: Double, z: Double, latitude: Double): Double {
        val sinLatitude = Math.sin(latitude)
        val n = divide.value(a, Math.sqrt(1 - eSquared * sinLatitude * sinLatitude))

        return if (Math.abs(Math.toDegrees(latitude)) != 90.0)
            divide.value(p, Math.cos(latitude)) - n
        else
            divide.value(z, sinLatitude) - n + eSquared * n
    }

}