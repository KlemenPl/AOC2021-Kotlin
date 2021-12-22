import kotlin.math.max
import kotlin.math.min

fun main() {
    val lines = get_input(22)

    /************
     * Part 1:
     ***********/
    val turnedOn = HashSet<Triple<Int, Int, Int>>()
    for (line in lines) {
        val parts = line.split("=")
        val x = parts[1].removeSuffix(",y").split("..")
        val y = parts[2].removeSuffix(",z").split("..")
        val z = parts[3].split("..")

        val x0 = max(x[0].toInt(), -50)
        val x1 = min(x[1].toInt(), 50)
        val y0 = max(y[0].toInt(), -50)
        val y1 = min(y[1].toInt(), 50)
        val z0 = max(z[0].toInt(), -50)
        val z1 = min(z[1].toInt(), 50)

        val add = line.startsWith("on")

        for (xI in x0..x1) {
            for (yI in y0..y1) {
                for (zI in z0..z1) {
                    val pos = Triple(xI, yI, zI)
                    if (add)
                        turnedOn.add(pos)
                    else
                        turnedOn.remove(pos)
                }
            }
        }
    }
    println("Part 1: ${turnedOn.size}")
    turnedOn.clear()

    /************
     * Part 2:
     ***********/
    // TODO:

}