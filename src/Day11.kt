fun main() {
    val lines = get_input(11)
    var input = lines.map { it.toCharArray().map { it2 -> it2.toString().toInt() } } as ArrayList<ArrayList<Int>>

    /************************
     * Part one:
     ************************/
    var numFlashes = 0
    for (i in 1..100) {
        for (y in 0 until input.size) {
            for (x in 0 until input[y].size) {
                input[y][x] += 1
            }
        }

        val flashed = HashSet<Pair<Int, Int>>()
        val toFlash = ArrayList<Pair<Int, Int>>()
        for (y in 0 until input.size) {
            for (x in 0 until input[y].size) {
                if (input[y][x] > 9) {
                    input[y][x] = 0
                    ++numFlashes
                    flashed.add(Pair(x, y))

                    toFlash.add(Pair(x, y + 1))
                    toFlash.add(Pair(x + 1, y + 1))
                    toFlash.add(Pair(x + 1, y))
                    toFlash.add(Pair(x + 1, y - 1))
                    toFlash.add(Pair(x, y - 1))
                    toFlash.add(Pair(x - 1, y - 1))
                    toFlash.add(Pair(x - 1, y))
                    toFlash.add(Pair(x - 1, y + 1))
                }
            }
        }

        while (toFlash.isNotEmpty()) {
            val pos = toFlash.removeLast()
            val x = pos.first
            val y = pos.second

            if (y < 0 || y >= input.size || x < 0 || x >= input[y].size)
                continue
            if (flashed.contains(pos))
                continue
            input[y][x] += 1
            if (input[y][x] <= 9)
                continue
            ++numFlashes
            input[y][x] = 0
            flashed.add(pos)
            toFlash.add(Pair(x, y + 1))
            toFlash.add(Pair(x + 1, y + 1))
            toFlash.add(Pair(x + 1, y))
            toFlash.add(Pair(x + 1, y - 1))
            toFlash.add(Pair(x, y - 1))
            toFlash.add(Pair(x - 1, y - 1))
            toFlash.add(Pair(x - 1, y))
            toFlash.add(Pair(x - 1, y + 1))
        }
    }
    println(numFlashes)


    /************************
     * Part two:
     ************************/
    input = lines.map { it.toCharArray().map { it2 -> it2.toString().toInt() } } as ArrayList<ArrayList<Int>>
    for (i in 1..10000) {
        for (y in 0 until input.size) {
            for (x in 0 until input[y].size) {
                input[y][x] += 1
            }
        }

        val flashed = HashSet<Pair<Int, Int>>()
        val toFlash = ArrayList<Pair<Int, Int>>()
        for (y in 0 until input.size) {
            for (x in 0 until input[y].size) {
                if (input[y][x] > 9) {
                    input[y][x] = 0
                    flashed.add(Pair(x, y))

                    toFlash.add(Pair(x, y + 1))
                    toFlash.add(Pair(x + 1, y + 1))
                    toFlash.add(Pair(x + 1, y))
                    toFlash.add(Pair(x + 1, y - 1))
                    toFlash.add(Pair(x, y - 1))
                    toFlash.add(Pair(x - 1, y - 1))
                    toFlash.add(Pair(x - 1, y))
                    toFlash.add(Pair(x - 1, y + 1))
                }
            }
        }

        while (toFlash.isNotEmpty()) {
            val pos = toFlash.removeLast()
            val x = pos.first
            val y = pos.second

            if (y < 0 || y >= input.size || x < 0 || x >= input[y].size)
                continue
            if (flashed.contains(pos))
                continue
            input[y][x] += 1
            if (input[y][x] <= 9)
                continue
            ++numFlashes
            input[y][x] = 0
            flashed.add(pos)
            toFlash.add(Pair(x, y + 1))
            toFlash.add(Pair(x + 1, y + 1))
            toFlash.add(Pair(x + 1, y))
            toFlash.add(Pair(x + 1, y - 1))
            toFlash.add(Pair(x, y - 1))
            toFlash.add(Pair(x - 1, y - 1))
            toFlash.add(Pair(x - 1, y))
            toFlash.add(Pair(x - 1, y + 1))
        }
        var sum = 0
        input.forEach { sum += it.sum() }
        if (sum == 0) {
            println("Step ${i}")
            break
        }
    }
}

