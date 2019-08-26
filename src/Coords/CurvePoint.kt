package Coords

import kotlin.math.pow

class CurvePoint(var point: Point, private var heading : Double, private var curvature : Double) : Point(point.x,point.y) {
    val radius : Double get() = curvature.pow((-1).toDouble())
}