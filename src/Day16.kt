import kotlin.text.StringBuilder

fun main() {
    val lines = get_input(16)
    val input = lines[0].map { it.digitToInt(16).toString(2).padStart(4,'0') }.joinToString("")

    
    println("Part 1: ${parsePacket(input).first}")
    println("Part 1: ${parsePacket2(input).first}")
}

fun parsePacket(str : String) : Pair<Int, Int> {
    var pos = 0
    val ver = str.substring(pos , pos + 3)
    pos += 3
    val type = str.substring(pos, pos + 3)
    pos += 3
    // println(str)
    var verSum = ver.toInt(2)

    if (type == "100") {
        // Literal value
        while (pos < str.length) {
            val c = str[pos++]
            pos += 4
            if (c == '0')
                break
        }
    } else {
        // Operator
        val c = str[pos++]

        val subVals = ArrayList<Int>()

        if (c == '0') {
            val len = str.substring(pos, pos + 15).toInt(2)
            pos += 15
            var start = 0
            while (start < len) {
                val out = parsePacket(str.substring(pos))
                subVals.add(out.first)
                verSum += out.first
                pos += out.second
                start += out.second
            }
        } else {
            val numPackets = str.substring(pos, pos + 11).toInt(2)
            pos += 11
            for (pIter in 1..numPackets) {
                val out = parsePacket(str.substring(pos))
                verSum += out.first
                pos += out.second

            }
        }

    }
    return Pair(verSum, pos)
}

fun parsePacket2(str : String) : Pair<Long, Int> {
    var pos = 0
    val ver = str.substring(pos , pos + 3)
    pos += 3
    val type = str.substring(pos, pos + 3)
    pos += 3
    // println(str)
    var verSum = 0L

    if (type == "100") {
        // Literal value
        val sb = StringBuilder()
        while (pos < str.length) {
            val c = str[pos++]
            sb.append(str.substring(pos, pos + 4))
            pos += 4
            if (c == '0')
                break
        }
        verSum = sb.toString().toLong(2)
    } else {
        // Operator
        val c = str[pos++]

        val subVals = ArrayList<Long>()

        if (c == '0') {
            val len = str.substring(pos, pos + 15).toInt(2)
            pos += 15
            var start = 0
            while (start < len) {
                val out = parsePacket2(str.substring(pos))
                subVals.add(out.first)
                pos += out.second
                start += out.second
            }
        } else {
            val numPackets = str.substring(pos, pos + 11).toInt(2)
            pos += 11
            for (pIter in 1..numPackets) {
                val out = parsePacket2(str.substring(pos))
                subVals.add(out.first)
                pos += out.second

            }
        }
        when ( type.toInt(2)) {
            0 -> verSum = subVals.sum()
            1 -> {
                var res = 1L
                subVals.forEach { res *= it }
                verSum = res
            }
            2 -> verSum = subVals.minOrNull()!!
            3 -> verSum = subVals.maxOrNull()!!
            5 -> verSum = if (subVals[0] > subVals[1]) 1 else 0
            6 -> verSum = if (subVals[0] < subVals[1]) 1 else 0
            7 -> verSum = if (subVals[0] == subVals[1]) 1 else 0
        }
    }

    return Pair(verSum, pos)
}