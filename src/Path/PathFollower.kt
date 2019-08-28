package Path

import Constraints.DriveKinematics
import Constraints.PIDFCoefficients
import Coords.Point
import Coords.Position
import Coords.State
import Extensions.*
import kotlin.math.*
import kotlin.system.measureNanoTime

class PathFollower(val co : PIDFCoefficients, val kinematics: DriveKinematics) {
    var time : Double = 0.0
    fun PIDF(robot : Position, lookDist: Double,left : Double, right : Double) : List<Double> {
        //TODO implement a REMOVE ALL calculations that do not pertain to PIDF (just pass in target and current wheel velocities)
        var list = listOf<Double>()
        time = measureNanoTime {
            val closest = State(0.0,0.0)
            val lookAhead = lookAhead(closest, robot, lookDist)
            val curvature = findCurvature(lookAhead, robot, lookDist)
            //array powers = fl, fr, bl, br
            //so for tank it should be (left, right, left, right)
            val desiredV : List<Double> = kinematics.getTargetVelocities(closest.velocity, curvature)
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

    fun lookAhead(closest : State, robot : Position, lookDist : Double) : Point {
        //TODO convert t to Point(x,y)
        val d = closest.next - closest
        val f = closest - robot
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

    fun findCurvature(lookAheadPoint : Point, robot : Position, lookDist : Double) : Double {
        val a = -tan(robot.heading.d2r())
        val b = 1.0
        val c = tan(robot.heading.d2r()) * robot.x - robot.y
        val x = abs(a * lookAheadPoint.x + b * lookAheadPoint.y + c)/ sqrt(a.pow(2) + b.pow(2))
        val side = sign(sin(robot.heading.d2r()) * (lookAheadPoint.x - robot.x) - cos(robot.heading.d2r()) * (lookAheadPoint.y - robot.y))
        val curvature = (2 * x / lookDist.pow(2)) * side;
        return curvature
    }
}