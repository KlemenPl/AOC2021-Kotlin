import kotlin.math.abs

fun main() {
    val lines = get_input(7)
    val input = lines[0].split(",").map { it.toInt() }.sorted()


    /****************
     * Part 1:
     ***************/
    var min = Int.MAX_VALUE
    val max = input.maxOrNull()
    for (i in 0..max!!) {
        var fuel = 0
        input.map { fuel += abs(it - i) }
        if (fuel < min)
            min = fuel
    }
    println("Min fuel needed: $min")

    val median =  if (input.size % 2 == 0)
        ((input[input.size / 2] + input[input.size / 2 - 1]) / 2)
     else
        (input[input.size / 2])
    println("Min fuel needed (n logn + n): ${input.sumOf { abs(it - median) }}")

    /****************
     * Part 2:
     ***************/
    min = Int.MAX_VALUE
    for (i in 0..max) {
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
