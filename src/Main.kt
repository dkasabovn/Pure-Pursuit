import Constraints.MotionConstraint
import Coords.*
import Path.PathGenerator

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val pts = mutableListOf<State>(
                State(0.0,0.0),
                State(10.0,0.0),
                State(10.0,10.0),
                State(15.0,15.0),
                State(20.0,17.0)
        )
        val path = PathGenerator.generate(pts, 2.0, .9,.1,.5, MotionConstraint(20.0, 2.0, 3.0))
        println("start")
        for (j in path) {
            println(j)
        }
        //State is printing (Dist, Vel)
    }
}
