import assertk.assertThat
import assertk.assertions.isEqualTo

fun <T : Number> solve(puzzle: (List<String>) -> T, withInput: String, andAssert: T? = null) {
    val input = readInput(withInput).dropLastWhile { it.isEmpty() }
    val result = puzzle(input)
    println(result)
    andAssert?.let { assertThat(result).isEqualTo(it) }
}
