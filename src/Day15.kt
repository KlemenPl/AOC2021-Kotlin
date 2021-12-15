class Point(val x: Int, val y: Int)

fun main() {
    val lines = get_input(15)
    val input = lines.map { line -> line.toCharArray().map { it.digitToInt() }.toTypedArray() }.toTypedArray()

    /************************
     * Part one:
     ************************/
    println("Part 1: ${getMinPath(input)}")


    /************************
     * Part one:
     ************************/
    // TODO: Way too slow, optimize!!!!
    val ySize = input.size * 5
    val xSize = input[0].size * 5

    val input2 = Array(ySize) { Array(xSize) { 0 } }
    for (y in 0 until ySize) {
        for (x in 0 until xSize) {
            if (y < input.size && x < input[0].size) {
                // From original
                input2[y][x] = input[y][x]
            } else if (x < input.size) {
                // From above
                input2[y][x] = (input2[y - input.size][x] + 1) % 10
            } else {
                // From left
                input2[y][x] = (input2[y][x - input.size] + 1) % 10
            }
            if (input2[y][x] == 0)
                input2[y][x] = 1
        }
    }


    println("Part 2: ${getMinPath(input2)}")

}

fun getMinPath(input: Array<Array<Int>>): Int {
    val ySize = input.size
    val xSize = input[0].size
    val cave = Array(ySize) { Array(xSize) { Int.MAX_VALUE } }
    val visited = Array(ySize) { Array(xSize) { false } }

    fun dfs(pos: Point, sum: Int) {
        if (pos.x < 0 || pos.x >= ySize ||
            pos.y < 0 || pos.y >= xSize
        )
            return
        cave[pos.x][pos.y] =
            if (cave[pos.x][pos.y] < input[pos.x][pos.y] + sum)
                cave[pos.x][pos.y]
            else
                input[pos.x][pos.y] + sum
    }

    cave[0][0] = 0
    while (!visited[ySize - 1][xSize - 1]) {
        var m0 = Int.MAX_VALUE
        var mx = -1
        var my = -1
        for (i in 0 until ySize) {
            for (j in 0 until xSize) {
                if (!visited[i][j] && cave[i][j] < m0) {
                    m0 = cave[i][j]
                    my = i
                    mx = j
                }
            }
        }

        visited[my][mx] = true
        dfs(Point(my - 1, mx), m0)
        dfs(Point(my + 1, mx), m0)
        dfs(Point(my, mx - 1), m0)
        dfs(Point(my, mx + 1), m0)
    }

    return cave[ySize - 1][xSize - 1]
}