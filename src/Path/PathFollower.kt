package Path

import Constraints.DriveKinematics
import Constraints.PIDFCoefficients
import Coords.Point
import Coords.State
import Extensions.dot
import Extensions.minus
import Extensions.plus
import Extensions.times
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.system.measureNanoTime

class PathFollower(val co : PIDFCoefficients, val kinematics: DriveKinematics) {
    var time : Double = 0.0
    fun PIDF(s : State, left : Double, right : Double) : List<Double> {
        //TODO implement a PIDF not just a PF
        var list = listOf<Double>()
        time = measureNanoTime {
            val closest = Point(0.0,0.0)
            val lookAhead = Point(0.0,0.0)
            //array powers = fl, fr, bl, br
            //so for tank it should be (left, right, left, right)
            val desiredV : List<Double> = kinematics.getTargetVelocities(s.velocity, s.curvature)
            val leftError = (desiredV[0] - left) * co.kP
            val rightError = (desiredV[1] - right) * co.kP
            val accelL = co.kA * ((leftError) / time)
            val accelR = co.kA * ((rightError) / time)
            val leftFF = co.kV * desiredV[0]
            val rightFF = co.kV * desiredV[1]
            val ffL = leftFF + accelL
            val ffR = rightFF + accelR
            val leftPower = ffL + leftError
            val rightPower = ffL + rightError
            list = listOf(leftPower, rightPower, leftPower, rightPower)
        }.toDouble()
        return list
    }

    fun lookAhead(s : State, robot : State, lookDist : Double) : Point {
        //TODO convert t to Point(x,y)
        val d = s.next - s
        val f = s - robot
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
            if (t0 >= 0 && t0 <= 1) {
                return (s + (d * t0))
            }
            if (t1 >= 0 && t1 <= 1) {
                return (s + (d * t1))
            }
            //no intersection
        }

        return Point(0.0,0.0)
    }
}