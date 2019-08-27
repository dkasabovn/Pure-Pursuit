import Coords.*
import Extensions.fuzzyEquals
import Extensions.interpolate
import Extensions.r2d
import Extensions.smooth
import Interfaces.EAccuracy
import Path.PathGenerator
import Spline.*
import kotlin.math.PI
import kotlin.math.log

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
        val path = PathGenerator(pts, 2.0)
        println("start")
        for (j in path.smooth(1.0-.1,.1, .5)) {
            println(j)
        }
    }
}
