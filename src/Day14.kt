fun main() {
    val lines = get_input(14)
    val insertRules = HashMap<String, Char>()
    for (i in 2 until lines.size) {
        val rule = lines[i].split(" -> ")
        insertRules[rule[0]] = rule[1][0]
    }

    /************************
     * Part one:
     ************************/
    var polymerTemplate = lines[0]
    val polymer = ArrayList<Char>()
    for (iter in 1..10) {
        for (i in 0..polymerTemplate.length - 2) {
            polymer.add(polymerTemplate[i])
            val pair = polymerTemplate[i].toString() + polymerTemplate[i + 1]
            val q = insertRules[pair]
            if (q != null)
                polymer.add(q)
        }
        polymer.add(polymerTemplate.last())
        polymerTemplate = polymer.joinToString("")
        polymer.clear()
        // println(polymerTemplate)
        // println("Iter $iter")
    }
    var min = Long.MAX_VALUE
    var max = Long.MIN_VALUE
    for (c in polymerTemplate) {
        val count = polymerTemplate.count { it == c }.toLong()
        if (count < min)
            min = count
        if (count > max)
            max = count
    }
    println("Part 1: ${max - min}")


    /************************
     * Part two:
     ************************/
    val pairs = calculateRules(lines[0], 40, insertRules)
    val counts = HashMap<Char, Long>()
    for(key in pairs.keys) {
        val keyCount = pairs[key]!!

        counts[key[0]] = counts.getOrDefault(key[0], 0L) + keyCount
        counts[key[1]] = counts.getOrDefault(key[1], 0L) + keyCount
    }

    min = Long.MAX_VALUE
    max = Long.MIN_VALUE
    for (count in counts.values) {
        if (count < min)
            min = count
        if (count > max)
            max = count
    }
    max /= 2
    min /= 2
    println("Part 2: ${max - min}")
    println("Part 2: ${max - min + 1}") // why???
}

fun calculateRules(polymer:String, steps : Int, insertRules:HashMap<String, Char>) :HashMap<String, Long>{
    var ruleCounts = HashMap<String, Long>()
    for (i in 0..polymer.length - 2) {
        val pair = polymer[i].toString() + polymer[i+1].toString()
        if (ruleCounts[pair] == null)
            ruleCounts[pair] = 1L
        else
            ruleCounts[pair] = ruleCounts[pair]!! + 1L
    }

    for (iter in 1 .. steps) {
        val newRuleCount = HashMap<String, Long>()
        for (key in ruleCounts.keys) {
            val pairCount: Long = ruleCounts.getOrDefault(key, 0L)
            val insert = insertRules[key]
            val newPair1 = key[0].toString() + insert.toString()
            val newPair2 = insert.toString() + key[1].toString()
            newRuleCount[newPair1] = newRuleCount.getOrDefault(newPair1, 0L) + pairCount
            newRuleCount[newPair2] = newRuleCount.getOrDefault(newPair2, 0L) + pairCount
        }
        ruleCounts = newRuleCount
    }
    return ruleCounts
}