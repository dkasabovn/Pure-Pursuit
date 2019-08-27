package Constraints

interface DriveKinematics {
    fun getTargetVelocities(v : Double, k : Double) : List<Double>
}