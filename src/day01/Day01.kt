package day01

import solve
import kotlin.math.absoluteValue
import kotlin.math.sign

class Dial(val range: IntRange = 0..99, start: Int = 50) {
    private var point = start
    val history = mutableListOf(point)
    var zeroCounts = 0

    fun turn(rotation: String) {
        print("st=$point\t")
        val amount = rotation.replace("L", "-").replace("R", "").toInt()
        repeat(amount.absoluteValue) {
            point += amount.sign
            if (point == range.first - 1) point = range.last
            if (point == range.last + 1) point = range.first
            if (point == 0) zeroCounts++
        }
        println("amt=$amount\tend=$point\tzc=$zeroCounts")
        history.add(point)
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val dial = Dial()
        input.forEach { dial.turn(it) }
        return dial.history.count { it == 0 }
    }

    fun part2(input: List<String>): Int {
        val dial = Dial()
        input.forEach { dial.turn(it) }
        return dial.zeroCounts
    }

    solve(::part1, withInput = "day01/test", andAssert = 3)
    solve(::part1, withInput = "day01/input", andAssert = 1100)

    solve(::part2, withInput = "day01/test", andAssert = 6)
    solve(::part2, withInput = "day01/input", andAssert = 6358)
}
