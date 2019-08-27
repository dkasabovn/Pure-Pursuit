package Path

import Constraints.DriveKinematics
import Constraints.PIDFCoefficients
import Coords.Point
import Coords.State
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
}