package Path

import Coords.*
import Extensions.minus
import Extensions.plus
import Extensions.times
import Math.Arc
import kotlin.math.abs
import kotlin.math.min

class PathGenerator {
    val out : MutableList<State> = mutableListOf()

    constructor(points: MutableList<State>, spacing: Double){
        inject(out, points, spacing)
        label()
    }

    constructor(points: MutableList<State>, spacing: Double, a : Double, b : Double, tol : Double){
        inject(out, points, spacing)
        smooth(a, b, tol)
        label()
    }

    fun inject(arr : MutableList<State>, points : MutableList<State>, spacing: Double) {
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
        out.forEachIndexed { index, state ->
            state.curvature = if (index > 0 && index < out.size-1) {
                Arc(out[index-1], state, out[index+1]).curvature
            } else {
                0.0
            }
            state.distance = sum
            sum += if (index < out.size-1) {
                state distance out[index+1]
            } else {
                0.0
            }
            state.velocity = min(0.0,1.0)
        }
    }
}