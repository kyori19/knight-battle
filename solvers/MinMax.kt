package solvers

import games.KnightBattle
import games.utils.Location

class MinMax(
    private val depth: Int,
) : Solver {
    override fun solve(game: KnightBattle): Location =
        game.candidates.maxBy { nl ->
            var fg = game.move(nl)
            if (fg.winner != null) {
                return@maxBy if (fg.winner == game.nextPlayer) {
                    Int.MAX_VALUE
                } else {
                    Int.MIN_VALUE
                }
            }
            repeat(1 + 2 * depth) {
                fg = fg.candidates.map(fg::move).minBy { it.score() }
                if (fg.winner != null) {
                    return@maxBy if (fg.winner == game.nextPlayer) {
                        Int.MAX_VALUE
                    } else {
                        Int.MIN_VALUE
                    }
                }
            }
            fg.score()
        }

    private fun KnightBattle.score(): Int = candidates.size
}
