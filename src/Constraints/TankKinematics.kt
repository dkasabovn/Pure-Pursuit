package Constraints

class TankKinematics(val w : Double) : DriveKinematics {
    override fun getTargetVelocities(v: Double, k: Double): List<Double> {
        val left = v * (2 + k*w)/2
        val right = v * (2 - k*w)/2
        return mutableListOf(left, right, left, right)
    }
}