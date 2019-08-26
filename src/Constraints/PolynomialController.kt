package Constraints

import Coords.Waypoint
import Extensions.interpolate
import kotlin.math.atan2

class PolynomialController(val startPose : Waypoint, val endPose : Waypoint) : DirectionController() {
    val line = startPose interpolate endPose
    override fun getHeading(t: Double): Double {
        val pt = line.getDerivative(t)
        return atan2(pt.y, pt.x)
    }
    override fun getDHeading(t: Double): Double {
        val pt = line.getDDerivative(t)
        return atan2(pt.y, pt.x)
    }
}