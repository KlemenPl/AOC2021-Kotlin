fun main() {
    val lines = get_input(6)

    /****************
     * Part 1:
     ***************/
    val fishesArr = ArrayList<Int>(lines.first().split(",").map { it.toInt() })
    for (iter in 1..80){
        val start_len = fishesArr.size
        for (i in 0 until start_len) {
            --fishesArr[i]
            if (fishesArr[i] == -1) {
                fishesArr[i] = 6
                fishesArr.add(8)
            }
        }

    }
    println("After 80 days: ${fishesArr.size}")

    /****************
     * Part 2:
     ***************/
    val fishes = Array<Long>(9){0}
    lines.first().split(",").map { fishes[it.toInt()] += 1L }
    for (iter in 1..256){
        val new_fish = fishes[0]
        for (i in 1 until  fishes.size) {
            fishes[i - 1] = fishes[i]
        }
        fishes[6] += new_fish
        fishes[8] = new_fish
    }
    println("After 256 days: ${fishes.sum()}")
}