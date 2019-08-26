package Constraints

import Spline.ParametricEquation
import kotlin.math.atan2

class LinearController(val line : ParametricEquation) : DirectionController() {
    override fun getHeading(t: Double): Double {
        val pt = line.getDerivative(t)
        return atan2(pt.y, pt.x)
    }

    override fun getDHeading(t: Double): Double {
        val pt = line.getDDerivative(t)
        return atan2(pt.y, pt.x)
    }
}