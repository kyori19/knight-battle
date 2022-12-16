package solvers

import games.KnightBattle
import games.utils.Location
import games.utils.Player

class MC(
    private val maxPlays: Int,
) : Solver {
    override fun solve(game: KnightBattle): Location =
        game.candidates.maxBy { loc ->
            val next = game.move(loc)
            (0 until maxPlays).count {
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
