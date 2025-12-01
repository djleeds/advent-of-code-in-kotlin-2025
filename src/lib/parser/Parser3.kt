package lib.parser

fun <T1, T2, T3> String.parse(block: Parser3<T1, T2, T3>.() -> Unit): ParseResult3<T1, T2, T3> {
    return Parser3<T1, T2, T3>(this).apply(block).result()
}

open class ParseResult3<out T1, out T2, out T3>(
    item1: T1,
    item2: T2,
    val item3: T3
) : ParseResult2<T1, T2>(item1, item2) {
    operator fun component3() = item3
}

open class Parser3<T1, T2, T3>(string: String) : Parser2<T1, T2>(string) {
    protected var item3: T3? = null
    fun result3(item: T3) = run { item3 = item }
    override fun result() = ParseResult3(item1!!, item2!!, item3!!)
}

context(parser: Parser3<T1, T2, T3>)
fun <T1, T2, T3> ParseSection.result3(transform: String.() -> T3) = parser.result3(transform(value))

context(parser: Parser3<T1, T2, String>)
fun <T1, T2> ParseSection.result3() = parser.result3(value)

context(parser: Parser3<T1, T2, List<T>>)
fun <T1, T2, T> ParseSections.resultMany3(transform: String.() -> T) =
    values.map { transform(it) }.also(parser::result3)

context(parser: Parser3<T1, T2, List<String>>)
fun <T1, T2> ParseSections.resultMany3() = parser.result3(values)
