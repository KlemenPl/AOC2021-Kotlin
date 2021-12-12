fun main() {
    val lines = get_input(12)

    val map = HashMap<String, ArrayList<String>>()
    lines.forEach {
        val caves = it.split("-")
        if (map[caves[0]] == null)
            map[caves[0]] = ArrayList<String>(5)
        if (map[caves[1]] == null)
            map[caves[1]] = ArrayList<String>(5)
        map[caves[0]]?.add(caves[1])
        map[caves[1]]?.add(caves[0])
    }

    /************************
     * Part one:
     ************************/
    val queue = ArrayDeque<String>()
    val queueVisited = ArrayDeque<HashSet<String>>()
    val startHS = HashSet<String>()
    startHS.add("start")
    queue.add("start")
    queueVisited.add(startHS)

    var num_paths = 0
    while (queue.isNotEmpty()) {
        val curr_cave = queue.removeFirst()
        val visited = queueVisited.removeFirst()
        if (curr_cave == "end") {
            ++num_paths
            continue
        }
        val caves = map[curr_cave] ?: continue
        for (cave in caves) {
            if (visited.contains(cave))
                continue
            val new_visited = HashSet<String>()
            new_visited.addAll(visited)
            if (cave[0].isLowerCase())
                new_visited.add(cave)
            queue.addLast(cave)
            queueVisited.addLast(new_visited)
        }
    }
    println(num_paths)

    /************************
     * Part one:
     ************************/
    num_paths = 0
    queue.clear()
    queueVisited.clear()
    val queueVisitedTwice = ArrayDeque<String>()
    startHS.add("start")
    queue.add("start")
    queueVisited.add(startHS)
    queueVisitedTwice.add("")

    while (queue.isNotEmpty()) {
        val curr_cave = queue.removeFirst()
        val visited = queueVisited.removeFirst()
        val visited_twice = queueVisitedTwice.removeFirst()
        if (curr_cave == "end") {
            ++num_paths
            continue
        }
        val caves = map[curr_cave] ?: continue
        for (cave in caves) {
            val new_visited = HashSet<String>()
            new_visited.addAll(visited)

            if (visited.contains(cave) &&visited_twice == "" && cave != "start" &&
                cave != "end") {
                queue.addLast(cave)
                queueVisited.addLast(new_visited)
                queueVisitedTwice.add(cave)
            } else if (!visited.contains(cave)) {
                if (cave[0].isLowerCase())
                    new_visited.add(cave)
                queue.addLast(cave)
                queueVisited.addLast(new_visited)
                queueVisitedTwice.add(visited_twice)
            }
        }
    }
    println("$num_paths (part 2)")

}

