package solvers

import games.KnightBattle
import games.utils.Location
import games.utils.Player

object Solver {
    private const val MAX_PLAYS = 1000

    fun solve(game: KnightBattle): Location =
        game.candidates.maxBy { loc ->
            val next = game.move(loc)
            (0 until MAX_PLAYS).count {
                playOut(next, game.nextPlayer)
            }
        }

    private fun playOut(game: KnightBattle, player: Player): Boolean =
        if (game.winner == null) {
            playOut(game.move(game.candidates.random()), player)
        } else {
            game.winner == player
        }
}
