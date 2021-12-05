fun main() {
    val lines = get_input(2)

    /************************
     * Part one:
     ************************/
    var horizontal :Long = 0
    var depth :Long= 0

    lines.forEach {
        val value = it.split(" ")[1].toInt()
        if (it.startsWith("forward"))
            horizontal += value
        else if (it.startsWith("up"))
            depth -= value
        else
            depth += value
    }
    println(horizontal * depth)


    /************************
     * Part two:
     ************************/
    var aim : Long = 0
    horizontal = 0
    depth = 0

    lines.forEach {
        val value = it.split(" ")[1].toInt()
        if (it.startsWith("forward")) {
            horizontal += value
            depth += value * aim
        }
        else if (it.startsWith("up"))
            aim -= value
        else
            aim += value
    }
    println(horizontal * depth)

    // lines.forEach{println(">  $it")}
}