package Constraints
//TODO to pass to motion profile generator
class MotionConstraint(var maxV: Double, var maxA: Double, var maxWV: Double, var maxWA: Double) {
    var maxJ: Double = 0.toDouble()
}
