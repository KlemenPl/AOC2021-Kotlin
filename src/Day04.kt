fun main() {
    val lines = get_input(4)

    val values = lines.first().split(",").map { it.toInt() }
    lines.removeFirst()
    lines.removeFirst()

    val boards = ArrayList<ArrayList<ArrayList<Int>>>()

    var start = 0
    for (i in 0 until lines.size) {
        if (lines[i].isEmpty()) {
            val board = ArrayList<ArrayList<Int>>()
            for (j in start until i) {
                board.add(lines[j].trim().split("\\s+".toRegex()).map { it.toInt() } as ArrayList<Int>)
            }
            boards.add(board)
            start = i + 1
        }
    }
    var board = ArrayList<ArrayList<Int>>()
    for (j in start until lines.size) {
        board.add(lines[j].trim().split("\\s+".toRegex()).map { it.toInt() } as ArrayList<Int>)
    }
    boards.add(board)

    for (i in values.indices) {
        val check = values[i]

        for (board in boards) {
            for (row in board) {
                for (j in row.indices) {
                    if (row[j] == check)
                        row[j] = -1
                }
            }
        }

        var b = 0
        while (b < boards.size) {
            board = boards[b]
            // Check horizontal
            var horizontal = true
            var vertical = true
            for (row in board) {
                horizontal = true
                for (j in row.indices) {
                    if (row[j] != -1) {
                        horizontal = false
                        break
                    }
                }
                if (horizontal)
                    break
            }

            for (j in board[0].indices) {
                vertical = true
                for (row in board) {
                    if (row[j] != -1) {
                        vertical = false
                        break
                    }
                }
                if (vertical)
                    break
            }

            if (horizontal || vertical) {
                // Sum vals
                var sum = 0
                board.forEach { row -> row.forEach { if (it != -1) sum += it } }
                //println(board.hashCode())
                println(sum * check)
                boards.remove(board)
                --b
            }

            ++b
        }

    }
}