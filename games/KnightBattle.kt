package games

import games.utils.*

data class KnightBattle(
    val size: Size,
    val history: List<Location> = listOf(),
) {
    private val depth = history.size
    val nextPlayer = when (depth % 2) {
        0 -> Player.PLAYER_ONE
        else -> Player.PLAYER_TWO
    }

    fun Location(x: Int, y: Int) = Location(size, x, y)

    private val current = history.lastOrNull()
    val candidates = current?.let { current ->
        knightMoves.mapNotNull { current.move(it) }.filter { it !in history }
    } ?: size.range.map { x ->
        size.range.map { y ->
            Location(x, y)
        }
    }.flatten()

    val winner: Player? = if (candidates.isEmpty()) { !nextPlayer } else { null }

    fun move(next: Location): KnightBattle {
        assert(next.isValid() && candidates.let { next in it })
        return copy(history = history + next)
    }

    override fun toString() = "KnightBattle[$size]${winner?.let { "<$it WIN>" } ?: ""}($history)"
}
