package games.utils

data class Location(
    val size: Size,
    val x: Int,
    val y: Int,
) {
    fun isValid() = x in size.range && y in size.range

    fun move(m: Move) = copy(x = x + m.dx, y = y + m.dy).takeIf { it.isValid() }

    override fun toString() = "($x, $y)"
}
