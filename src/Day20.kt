fun main() {
    val lines = get_input(20)

    val algorithm = lines[0].map { it != '.' }.toTypedArray()
    lines.removeIf { it.isBlank() }

    fun applyIterations(n : Int) : Int {
        val image = lines.subList(1, lines.size).map {
            it.toCharArray().map { c -> c != '.' }.toCollection(ArrayList())
        }.toCollection(ArrayList())
        var processedImage = image
        var infinityState = false
        for (i in 1..n) {
            processedImage = applyAlgorithm(algorithm, processedImage, infinityState)
            infinityState = !infinityState
            // processedImage.forEach { it.forEach { c -> print(if (c) '#' else '.') }; println() }
            // println()
        }
        var count = 0
        processedImage.forEach { it -> count += it.count { it } }
        return count
    }

    /*************
     * Part 1:
     ************/
    var count = applyIterations(2)
    println("After 2 iterations: $count")

    /*************
     * Part 2:
     ************/
    count = applyIterations(50)
    println("After 50 iterations: $count")


}


fun applyAlgorithm(
    algorithm: Array<Boolean>, image: ArrayList<ArrayList<Boolean>>,
    infinityState: Boolean
): ArrayList<ArrayList<Boolean>> {
    val filler = if (infinityState) algorithm.last() else algorithm.first()
    val filling = ArrayList<Boolean>(image[0].size + 2)
    for (i in 0 until image.size) {
            image[i].add(0, filler)
            image[i].add(filler)
    }
    for (i in 1..image[0].size)
        filling.add(filler)
    image.add(0, filling.toCollection(ArrayList()))
    image.add(filling.toCollection(ArrayList()))
    val out = image.map { it.toCollection(ArrayList()) }.toCollection(ArrayList())

    fun get(i: Int, j: Int): Int {
        if (i > 0 && i < image.size - 1 &&
            j > 0 && j < image[i].size - 1
        )
            return if (image[i][j]) 1 else 0
        return if (infinityState) 1 else 0
    }

    // Applying algorithm
    for (i in 0 until image.size) {
        for (j in 0 until image[i].size) {
            val idx =
                get(i - 1, j - 1) * 256 +
                        get(i - 1, j + 0) * 128 +
                        get(i - 1, j + 1) * 64 +
                        get(i + 0, j - 1) * 32 +
                        get(i + 0, j + 0) * 16 +
                        get(i + 0, j + 1) * 8 +
                        get(i + 1, j - 1) * 4 +
                        get(i + 1, j + 0) * 2 +
                        get(i + 1, j + 1) * 1
            out[i][j] = algorithm[idx]
        }
    }
    return out
}
