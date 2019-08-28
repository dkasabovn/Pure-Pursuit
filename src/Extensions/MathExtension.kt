package Extensions

import Coords.Point
import Coords.Waypoint
import Spline.ParametricEquation
import Spline.QHS
import kotlin.math.PI
import kotlin.math.abs

fun Double.fuzzyEquals(b: Double, tolerance: Double): Boolean {
    return abs(this - b) < tolerance
}

//TODO create a function that soothes out an array of double so that the maximum delta between two
//indices is drop
fun MutableList<Double>.smooth(drop : Double) {
    for (i in this) {
        println(i)
    }
}

infix fun Waypoint.interpolate(p1 : Waypoint) : ParametricEquation = QHS(this, p1).parametric

infix operator fun Point.minus(other : Point) : Point = Point(this.x-other.x, this.y-other.y)

infix operator fun Point.times(other : Double) : Point = Point(this.x * other, this.y * other)

infix operator fun Point.plus(other : Point) : Point = Point(this.x + other.x, this.y + other.y)



infix fun Point.dot(other: Point) : Double = (this.x*other.x) + (this.y*other.y)

fun Double.r2d() : Double = this * (180/ PI)

fun Double.d2r() : Double = this * (PI/180)