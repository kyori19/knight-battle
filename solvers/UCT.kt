package solvers

import games.KnightBattle
import games.utils.Location
import games.utils.Player
import kotlin.math.log2
import kotlin.math.sqrt

class UCT(
    private val maxPlays: Int,
    private val ucb: Double,
) : Solver {

    override fun solve(game: KnightBattle): Location {
        val hist: Hist = game.candidates.associateWith { loc ->
            playOut(game.move(loc), game.nextPlayer) to 1
        }.toMutableMap()

        while (hist.total < maxPlays) {
            val (loc, p) = hist.maxBy { (_, p) -> (p.first.toDouble() / p.second) + sqrt(ucb * 2 * log2(hist.total.toDouble()) / p.second) }
            hist[loc] = p.first + playOut(game.move(loc), game.nextPlayer) to p.second + 1
        }

        return hist.maxBy { (_, p) -> p.first.toDouble() / p.second }.key
    }

    private fun playOut(game: KnightBattle, player: Player): Int =
        if (game.winner == null) {
            playOut(game.move(game.candidates.random()), player)
        } else {
            if (game.winner == player) { 1 } else { 0 }
        }
}

private typealias Hist = MutableMap<Location, Pair<Int, Int>>

private val Hist.total: Int
    get() = values.sumOf(Pair<Any, Int>::second)
