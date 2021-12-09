fun main() {
    val lines = get_input(9).map { it -> it.map { it.toString().toInt() } } as ArrayList<ArrayList<Int>>

    /************************
     * Part one:
     ************************/
    var risk = 0
    for (i in lines.indices) {
        for (j in 0 until lines[i].size) {
            val curr = lines[i][j]
            if ((i == 0 || lines[i - 1][j] > curr) && // Up?
                (j == 0 || lines[i][j - 1] > curr) &&  // Left?
                (i == lines.size - 1 || lines[i + 1][j] > curr) &&  // Down?
                (j == lines[i].size - 1 || lines[i][j + 1] > curr) //Right?
            )
                risk += curr + 1
        }
    }
    println(risk)

    /************************
     * Part two:
     ************************/
    val basin_sizes = ArrayList<Int>()
    for (i in lines.indices) {
        for (j in 0 until lines[i].size) {
            val curr = lines[i][j]
            if (curr == -1)
                continue
            basin_sizes.add(calculate_basin_size(lines, i, j))
        }
    }
    println(basin_sizes.sorted().reversed())
    risk = 1
    basin_sizes.sorted().reversed().subList(0, 3).forEach { risk *= it }
    println(risk)

}


fun calculate_basin_size(input: ArrayList<ArrayList<Int>>, x: Int, y: Int): Int {
    // Is out of range?
    if (x < 0 || x >= input.size || y < 0 || y >= input[x].size)
        return 0

    val curr = input[x][y]
    if (curr == -1 || curr == 9)
        return 0
    input[x][y] = -1

    var size = 1
    size += calculate_basin_size(input, x - 1, y)
    size += calculate_basin_size(input, x, y - 1)
    size += calculate_basin_size(input, x + 1, y)
    size += calculate_basin_size(input, x, y + 1)
    return size
}

