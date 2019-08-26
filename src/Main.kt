import Constraints.DirectionController
import Constraints.LinearController
import Coords.*
import Extensions.fuzzyEquals
import Extensions.interpolate
import Extensions.r2d
import Extensions.smooth
import Interfaces.EAccuracy
import Spline.*
import kotlin.math.PI

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val a = Waypoint(0.0, 0.0, 100.0, 11.0, 20.0, 30.0)
        val b = Waypoint(93.0, 70.0, 24.0, 72.0, 15.0, 75.0)
        val i = a interpolate b
        val j : DirectionController = LinearController(i)
        println(i)
        var ctr = 0.0;
        while (ctr <= 1.0) {
            println(j.getHeading(ctr))
            ctr += .01
        }
    }
}
