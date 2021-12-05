fun main() {
    val lines = get_input(5)

    val board = Array(1000) { Array(1000) { 0 } }

    for (line in lines) {
        val segments = line.split(" -> ")
        var x0 = segments[0].split(",")[0].toInt()
        var y0 = segments[0].split(",")[1].toInt()

        val x1 = segments[1].split(",")[0].toInt()
        val y1 = segments[1].split(",")[1].toInt()

        // Part 1 check:
        // if (x0 != x1 && y0 != y1)
        //     continue

        board[x0][y0] += 1
        while (x0 != x1 || y0 != y1) {
            if (x0 != x1) {
                if (x0 > x1)
                    --x0
                else
                    ++x0
            }
            if (y0 != y1) {
                if (y0 > y1)
                    --y0
                else
                    ++y0
            }
            board[x0][y0] += 1
        }

    }

    // Count
    var count = 0
    for (row in board) {
        for (value in row) {
            if (value >= 2)
                ++count
        }
    }
    println(count)
}

