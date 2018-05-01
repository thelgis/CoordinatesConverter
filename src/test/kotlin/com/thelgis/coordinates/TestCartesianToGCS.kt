package com.thelgis.coordinates

import org.junit.Assert
import org.junit.Test

class TestCartesianToGCS {

    @Test
    fun `cartesian to GGS conversion works for GCS point 0,0,0`() {
        val (lat, long, elevation) = CartesianToGCS.convert(6378137, 0, 0)
        Assert.assertTrue(lat == 0.0)
        Assert.assertTrue(long == 0.0)
        Assert.assertTrue(elevation == 0.0)
    }

}