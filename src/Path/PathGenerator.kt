package Path

import Coords.*
import Extensions.minus
import Extensions.plus
import Extensions.times
import Math.Arc
import kotlin.math.abs

class PathGenerator constructor(points : MutableList<State>, var spacing : Double){
    val out : MutableList<State> = mutableListOf()
    init {
        inject(out, points)
    }

    constructor(points: MutableList<State>, spacing: Double, a : Double, b : Double, tol : Double) : this(points, spacing) {
        inject(out, points)
        smooth(a, b, tol)
    }

    fun inject(arr : MutableList<State>, points : MutableList<State>) {
        for (i in 0 until points.size-1) {
            val a = points[i].location
            val b = points[i+1].location
            var vi = b - a
            val pointCount = Math.ceil(vi.magnitude/spacing)
            vi = vi.unitVector * spacing
            for (j in 0 until pointCount.toInt()) {
                val pt = a + (vi * j.toDouble())
                arr.add(State(pt.x, pt.y))
            }
        }
        arr.add(points.last())
    }

    fun smooth(a : Double, b : Double, tol : Double) : MutableList<State> {
        val new : MutableList<State> = out.toMutableList()
        var c = tol

        while (c >= tol) {
            c = 0.0
            for (i in 1 until out.size-1) {
                val auxX = new[i].x
                new[i].x += a * (out[i].x - new[i].x) + b * (new[i-1].x + new[i+1].x - (2.0 * new[i].x))
                c += abs(auxX - new[i].x)
                val auxY = new[i].y
                new[i].y += a * (out[i].y - new[i].y) + b * (new[i-1].y + new[i+1].y - (2.0 * new[i].y))
                c += abs(auxY - new[i].y)
            }
        }
        return new
    }
    //TODO add velocity calculations
    fun label() {
        var sum = 0.0
        for (i in 1 until out.size-1) {
            val arc = Arc(out[i-1].location, out[i].location, out[i+1].location)
            sum += out[i-1].location distance out[i].location
            val vel = 0.0
            out[i].curvature = arc.curvature
            out[i].distance = sum
            out[i].velocity = vel
        }
    }
}