package day02

import kotlin.io.path.Path
import kotlin.io.path.readText

var debugMode = false

fun println(string: String) {
    if (debugMode) kotlin.io.println(string)
}

fun main() {
    fun read(file: String): String = Path("src/day02/$file.txt").readText().trim()

    fun parse(input: String): List<Long> = input.split(",").flatMap {
        val ends = it.split("-")
        LongRange(ends.first().toLong(), ends.last().toLong())
    }

    fun part1(file: String): Long {
        val parsed = parse(read(file))
        println("Count: ${parsed.size}")

        val invalidIds = mutableListOf<Long>()

        for (id in parsed) {
            val idString = id.toString()
            if (idString.length.rem(2) == 0) {
                val half = idString.length / 2
                val firstHalf = idString.take(half)
                val secondHalf = idString.drop(half)
                if (firstHalf == secondHalf) invalidIds.add(id)
            }
        }

        return invalidIds.sum()
    }

    fun part2(file: String): Long {
        val parsed = parse(read(file))
        val maxCharacters = parsed.max().toString().length

        println("Count: ${parsed.size}")
        println("Max: $maxCharacters")

        val invalidIds = mutableListOf<Long>()

        for (id in parsed) {
            val idString = id.toString()
            val maxChunkSize = idString.length / 2
            for (chunkSize in 1..maxChunkSize) {
                val chunks = idString.chunked(chunkSize)
                val invalid = chunks.all { it == chunks.first() }
                println("$id @ $chunkSize/$maxChunkSize -> $chunks -> $invalid")
                if (invalid) {
                    invalidIds.add(id)
                    break
                }
            }
        }

        return invalidIds.sum()
    }

    println(part1("test"))
    println(part1("input"))

    println(part2("test"))
    println(part2("input"))
}
