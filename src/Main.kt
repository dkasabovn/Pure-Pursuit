import Constraints.DriveKinematics
import Constraints.MotionConstraint
import Constraints.PIDFCoefficients
import Constraints.TankKinematics
import Coords.*
import Extensions.fuzzyEquals
import Extensions.minus
import Extensions.plus
import Extensions.times
import Path.PathFollower
import Path.PathGenerator
import java.lang.Math.atan2
import kotlin.math.pow

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val pts = mutableListOf(State(0.0,0.0), State(40.0,0.0), State(80.0, 0.0))
        val path = PathGenerator.generate(mutableListOf(State(0.0,0.0), State(58.0,0.0), State(67.0,-4.0), State(68.0,-7.0), State(68.0,-12.0), State(68.0,-16.0)), 5.0,.9,.1,1.0,MotionConstraint(40.0,10.0,1.0))
        val follower = PathFollower(path,10.0, TankKinematics(5.0), PIDFCoefficients(0.0,0.0,0.0,0.0,0.0), 5.0)
        println("start")
        var time = 0.0
        var roloc = Position(0.0,0.0,0.0)
        var closest = follower.closestWaypoint(roloc)
        while (closest.velocity != 0.0) {
//            println("Closest ${follower.closestWaypoint(roloc)}")
            follower.followPath(roloc, 10.0,10.0,   .5)

            closest = follower.closestWaypoint(roloc)
            val additionV = (closest.next - (roloc as Point)).unitVector
            val newRoloc = (roloc + additionV)
            roloc = Position(newRoloc.x, newRoloc.y, atan2(additionV.y,additionV.x))
            time += .005
            println(closest)
        }
        //State is printing (Dist, Vel)
    }
}
