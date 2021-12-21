class Player(var pos: Int, var score: Int)

fun main() {
    val lines = get_input(21)
    val startPos1 = lines[0].split(" ").last().toInt()
    val startPos2 = lines[1].split(" ").last().toInt()

    var p1 = Player(startPos1, 0)
    var p2 = Player(startPos2, 0)

    var dice = 0
    fun getDice(): Int {
        return ++dice
    }

    fun applyDice(p: Player) {
        var roll = 0
        for (i in 1..3) {
            roll += getDice()
        }
        p.pos = (p.pos + roll - 1) % 10 + 1
        p.score += p.pos
        // println("Pos: ${p.pos}, Score: ${p.score}")
    }

    /************
     * Part 1:
     ***********/

    while (true) {
        applyDice(p1)
        if (p1.score >= 1000)
            break
        applyDice(p2)
        if (p2.score >= 1000)
            break
    }

    if (p1.score > p2.score)
        println("Part 1: ${p2.score * dice}")
    else
        println("Part 1: ${p1.score * dice}")

    /************
     * Part 2:
     ***********/

    fun diceRange(num : Int) : Int{
        return when (num) {
            3 -> 1 // 111
            4 -> 3 // 112, 121, 211
            5 -> 6 // 113, 131, 311, 122, 212, 221
            6 -> 7 // 123, 132, 213, 231, 312, 321, 222
            7 -> 6 // 223, 232, 322, 133, 313, 331
            8 -> 3 // 233, 323, 332
            9 -> 1 // 333
            else -> 0
        }
    }
    fun simulate(p1 : Player, p2 : Player, turn : Boolean): Pair<Long, Long> {
        if (p1.score >= 21)
            return Pair(1, 0)
        else if (p2.score >= 21)
            return Pair(0, 1)
        var win1 = 0L
        var win2 = 0L
        val curr = if (turn)  p1 else p2
        for (roll in 3..9) {
            val old = Player(curr.pos, curr.score)
            curr.pos = (curr.pos + roll - 1) % 10 + 1
            curr.score += curr.pos
            val s = simulate(p1, p2, !turn)
            val multiplier = diceRange(roll)
            win1 += s.first * multiplier
            win2 += s.second * multiplier
            // Reset player
            curr.pos = old.pos
            curr.score = old.score
        }
        return Pair(win1, win2)
    }

    p1 = Player(startPos1, 0)
    p2 = Player(startPos2, 0)
    val res = simulate(p1, p2, true)
    println("Part 2: ${res.first}:${res.second}; ${if (res.first > res.second) res.first else res.second}")
}

