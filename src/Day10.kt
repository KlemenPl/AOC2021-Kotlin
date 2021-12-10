import java.util.*

fun main() {
    val lines = get_input(10)

    /************************
     * Part one:
     ************************/
    var points = 0L
    for (line in lines) {
        val stack = Stack<Char>()
        for (c in line) {
            when (c) {
                '(' -> stack.push(')')
                '[' -> stack.push(']')
                '{' -> stack.push('}')
                '<' -> stack.push('>')
                else -> {
                    val top = stack.pop()
                    if (top != c) {
                        points += when (c) {
                            ')' -> 3
                            ']' -> 57
                            '}' -> 1197
                            '>' -> 25137
                            else -> 0
                        }
                    }
                }
            }
        }
    }
    println(points)

    /************************
     * Part two:
     ************************/
    val pointsArr = ArrayList<Long>(lines.size)
    for (line in lines) {
        val stack = Stack<Char>()
        points = 0
        var flag = true
        for (c in line) {
            when (c) {
                '(' -> stack.push(')')
                '[' -> stack.push(']')
                '{' -> stack.push('}')
                '<' -> stack.push('>')
                else -> {
                    val top = stack.pop()
                    if (top != c) {
                        flag = false
                        break
                    }
                }
            }

        }

        while (flag && !stack.empty()) {
            val top = stack.pop()
            points = points * 5 + when (top) {
                ')' -> 1
                ']' -> 2
                '}' -> 3
                '>' -> 4
                else -> 0
            }
        }
        if (flag)
            pointsArr.add(points)
    }
    println(pointsArr.sorted())
    println(pointsArr.sorted()[pointsArr.size / 2])
}