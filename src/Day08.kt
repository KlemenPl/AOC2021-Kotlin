fun main() {
    val lines = get_input(8)
    println(lines)

    /************************
     * Part one:
     ************************/
    var amount = 0
    for (line in lines) {
        val parts = line.split(" | ")[1].split(" ")
        for (part in parts) {
            if (part.length == 1 || part.length == 2 || part.length == 3 ||
                part.length == 4 || part.length == 7)
                ++amount
        }
    }
    println(amount)

    /************************
     * Part two:
     ************************/
    amount = 0
    for (line in lines) {
        val signals = line.split(" | ")[0].split(" ")
        val outputs = line.split(" | ")[1].split(" ")
        // TODO
    }
}