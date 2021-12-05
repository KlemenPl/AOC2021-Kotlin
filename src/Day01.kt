fun main() {
    val lines = get_input(1)

    /************************
     * Part one:
     ************************/
    var prev = lines[0].toInt()
    var counter = 0
    for (i in 1 until lines.size) {
        val curr = lines[i].toInt()
        if (curr > prev)
            ++counter
        prev = curr
    }
    println(counter)


    /************************
     * Part two:
     ************************/

        var prevSum = lines[0].toInt() +
                lines[1].toInt() +
                lines[2].toInt()
        var prev2 = lines[1].toInt()
        var prev1 = lines[2].toInt()
        counter = 0
        for (i in 3 until lines.size) {
            val curr = lines[i].toInt()
            val currSum = curr + prev2 + prev1
            if (currSum > prevSum)
                ++counter
            prevSum = currSum
            prev2 = prev1
            prev1 = curr
        }
        println(counter)
        // lines.forEach{println(">  $it")}

}