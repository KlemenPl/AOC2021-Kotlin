import kotlin.math.max
import kotlin.math.sign

fun main() {
    val lines = get_input(17)
    val line = lines[0].substringAfter("target area: ").replace("x=", "").replace("y=", "")
    val parts = line.split(", ")
    val areaX = parts[0].split("..").map { it.toInt() }
    val areaY = parts[1].split("..").map { it.toInt() }

    var maxHeight = 0
    var count = 0
    for (vX in -500..500) {
        for (vY in -500..500) {
            if (vX == 0 && vY == 0)
                continue
            var x = 0
            var y = 0
            var vX0 = vX
            var vY0 = vY
            var mY = y
            var pastTarget = false
            while (!pastTarget) {
                x += vX0
                y += vY0
                vX0 -= vX0.sign
                vY0 -= 1

                mY = max(mY, y)

                if (x >= areaX[0] && x <= areaX[1] &&
                    y >= areaY[0] && y <= areaY[1]) {
                    ++count
                    // println("$x, $y  $mY")
                    maxHeight = max(mY, maxHeight)
                    break
                } else if (x > areaX[1] || y < areaY[0])
                    pastTarget = true

            }
        }
    }

    println("Part 1: $maxHeight")
    println("Part 2: $count")

}