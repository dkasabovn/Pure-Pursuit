package Path

import Constraints.DriveKinematics
import Constraints.PIDFCoefficients
import Coords.Point
import Coords.Position
import Coords.State
import Extensions.*
import kotlin.math.*
import Math.Arc
import kotlin.system.measureTimeMillis

class PathFollower constructor(val path : MutableList<State>, val lookDist : Double,val robotPosition : () -> Position, val kinematics: DriveKinematics, val co : PIDFCoefficients) {
    var lastClosest : Int = 0
    var loopTime : Double = 50.0
    fun closestWaypoint() : State {
        val rloc = robotPosition()
        var minDist = Double.MAX_VALUE
        var closestPoint = path.get(0)
        for (i in lastClosest until path.size) {
            val dist = rloc distance path[i]
            if (minDist > dist) {
                minDist = dist
                closestPoint = path[i]
                lastClosest = i
            }
        }
        return closestPoint;
    }

    fun followPath() {
        val closestPoint = closestWaypoint()
        val curvature = findCurvature()
        val desiredVelocity = kinematics.getTargetVelocities(closestPoint.velocity, curvature)
        val powers = PIDF(0.0,0.0, desiredVelocity)
    }
    //TODO add ways to get left and right wheel velocities
    fun PIDF(left : Double, right : Double, desiredVelocities : List<Double>) : List<Double> {
        var list = listOf<Double>()
        loopTime = measureTimeMillis {
            val eL = desiredVelocities[0] - left
            val eR = desiredVelocities[1] - right
            val ffl = (co.kV * desiredVelocities[0]) + (co.kA * (eL)/loopTime)
            val ffr = (co.kV * desiredVelocities[1]) + (co.kA * (eR)/loopTime)
            val leftPower = ffl + eL * co.kP
            val rightPower = ffl + eR * co.kP
            list = listOf(leftPower, rightPower, leftPower, rightPower)
        }.toDouble()
        return list
    }

    fun lookAhead() : Point {
        val closest = closestWaypoint()
        val rloc = robotPosition()
        val d = closest.next - closest
        val f = closest - rloc
        val a = d dot d
        val b = 2 * (f dot d)
        val c = (f dot f) - lookDist.pow(2)
        var discrim = b.pow(2) - 4 * a * c
        if (discrim < 0) {
            //No
        } else {
            discrim = sqrt(discrim)
            val t0 : Double = (-b - discrim) / (2.0 * a)
            val t1 : Double = (-b + discrim) / (2.0 * a)
            if (t0 in 0.0..1.0) {
                return (closest + (d * t0))
            }
            if (t1 in 0.0..1.0) {
                return (closest + (d * t1))
            }
            //no intersection
        }

        return Point(0.0,0.0)
    }

    fun findCurvature() : Double {
        val rloc = robotPosition()
        val lookAheadPoint = lookAhead()
        val a = -tan(rloc.heading.d2r())
        val b = 1.0
        val c = tan(rloc.heading.d2r()) * rloc.x - rloc.y
        val x = abs(a * lookAheadPoint.x + b * lookAheadPoint.y + c) / sqrt(a.pow(2) + b.pow(2))
        val side = sign(sin(rloc.heading.d2r()) * (lookAheadPoint.x - rloc.x) - cos(rloc.heading.d2r()) * (lookAheadPoint.y - rloc.y))
        val curvature = (2 * x / lookDist.pow(2)) * side;
        return curvature
    }

    fun findArc() : Arc {
        val r = findCurvature().pow(-1)
        val rloc = robotPosition()
        val theta = rloc.heading
        var center = Point(cos(theta-90.0.d2r()), cos(theta-90.0.d2r()))
        center *= r
        var coords = center + rloc
        return Arc(r, coords.x, coords.y)
    }
}