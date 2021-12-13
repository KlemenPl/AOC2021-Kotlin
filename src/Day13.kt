fun main() {
    val lines = get_input(13)
    val folds = ArrayDeque<Pair<Char, Int>>()
    val points = ArrayDeque<Pair<Int, Int>>()
    lines.forEach {
        if (it.isNotEmpty()) {
            if (it.contains("fold")) {
                val parts = it.split(" ")[2].split("=")
                folds.addLast(Pair(parts[0][0], parts[1].toInt()))
            } else {
                val parts = it.split(",").map { it.toInt() }
                points.addLast(Pair(parts[0], parts[1]))
            }
        }
     }

    var xMax = 2000
    var yMax = 2000
    var first = true
    for (fold in folds) {
        val size = points.size
        for (i in 0 until size) {
            val point = points.removeFirst()
            val newPoint = if (fold.first == 'x') {
                xMax = fold.second
                Pair(2*fold.second - point.first, point.second)
            } else {
                yMax = fold.second
                Pair(point.first, 2*fold.second - point.second)
            }

            if (!points.contains(point) && point.first in 0 until xMax&&
                    point.second in 0 until yMax)
                points.addLast(point)
            else if (!points.contains(newPoint))
                points.addLast(newPoint)
        }
        if (first) {
            println("Part 1: ${points.size}")
            first = false;
        }
    }

    for (i in 0..yMax) {
        for (j in 0..xMax) {
            if (points.contains(Pair(j, i)))
                print("*")
            else
                print(" ")
        }
        println()
    }
}