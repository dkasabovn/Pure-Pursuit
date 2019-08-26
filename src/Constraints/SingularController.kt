package Constraints

class SingularController(val angle : Double) : DirectionController() {
    override fun getHeading(t: Double): Double {
        return angle
    }

    override fun getDHeading(t: Double): Double {
        return 0.0
    }
}