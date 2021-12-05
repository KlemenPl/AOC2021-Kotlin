import java.util.function.Predicate

fun main() {
    var lines = get_input(3)

    /************************
     * Part one:
     ************************/
    var gamma_rate = ""
    var epsilon_rate = ""
    for (i in 0 until lines[0].length) {
        var zeroCount = 0
        var oneCount = 0
        for (j in 0 until lines.size) {
            when (lines[j][i]) {
                '1' -> ++oneCount
                '0' -> ++zeroCount
            }
        }
        if (zeroCount > oneCount) {
            gamma_rate += "0"
            epsilon_rate += "1"
        } else {
            gamma_rate += "1"
            epsilon_rate += "0"
        }

    }

    println(epsilon_rate.toInt(2) * gamma_rate.toInt(2))

    /************************
     * Part two:
     ************************/
    val linesCopy : ArrayList<String> = lines.clone() as ArrayList<String>

    for (i in 0 until lines[0].length) {
        var zeroCount = 0
        var oneCount = 0
        for (j in 0 until lines.size) {
            when (lines[j][i]) {
                '1' -> ++oneCount
                '0' -> ++zeroCount
            }
        }
        if (zeroCount > oneCount) {
            lines.removeIf { it.substring(i).startsWith('1') }
        } else {
            lines.removeIf { it.substring(i).startsWith('0') }
        }
        if (lines.size == 1)
            break
    }
    val oxygen = lines[0].toInt(2)

    lines = linesCopy
    for (i in 0 until lines[0].length) {
        var zeroCount = 0
        var oneCount = 0
        for (j in 0 until lines.size) {
            when (lines[j][i]) {
                '1' -> ++oneCount
                '0' -> ++zeroCount
            }
        }
        if (zeroCount <= oneCount) {
            lines.removeIf { it.substring(i).startsWith('1') }
        } else {
            lines.removeIf { it.substring(i).startsWith('0') }
        }
        if (lines.size == 1)
            break
    }
    val co2 = lines[0].toInt(2)
    println(oxygen * co2)

}