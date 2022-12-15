package games.utils

data class Size(
    val size: Int,
) {
    private val max = (size - 1) / 2
    private val min = max - size + 1
    val range = min..max

    override fun toString() = size.toString()
}

val Int.size: Size
    get() = Size(this)
