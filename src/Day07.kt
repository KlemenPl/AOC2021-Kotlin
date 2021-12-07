import kotlin.math.abs

fun main() {
    val lines = get_input(7)
    val input = lines[0].split(",").map { it.toInt() }


    /****************
     * Part 1:
     ***************/
    var min = Int.MAX_VALUE
    for (i in 1..input[0]) {
        val to_pos = i
        var fuel = 0
        input.map { fuel += abs(it - to_pos) }
        if (fuel < min)
            min = fuel
    }
    println("Min fuel needed: $min")

    /****************
     * Part 2:
     ***************/
    min = Int.MAX_VALUE
    for (i in 1..input[0]) {
        val to_pos = i
        var fuel = 0
        input.map {
            val dif = abs(it - to_pos)
            fuel += dif * (dif + 1) / 2
        }
        if (fuel < min)
            min = fuel

    }
    println("Min fuel needed 2: $min")
}
