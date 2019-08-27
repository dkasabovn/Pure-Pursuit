package Constraints

data class MotionConstraint(val maxV : Double, val maxA : Double, val k : Double)
//K should be a value btw 1-5 and limits speed in proportion to curvature