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

    private val current = history.lastOrNull()
    val candidates = current?.let { current ->
        knightMoves.mapNotNull { current.move(it) }.filter { it !in history }
    }

    val winner: Player? = if (candidates?.size == 0) { !nextPlayer } else { null }

    fun move(next: Location): KnightBattle {
        assert(next.isValid() && candidates?.let { next in it } ?: true)
        return copy(history = history + next)
    }

    override fun toString() = "KnightBattle[$size]${winner?.let { "<$it WIN>" } ?: ""}($history)"
}
