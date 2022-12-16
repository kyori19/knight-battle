package solvers

import games.KnightBattle
import games.utils.Location

interface Solver {
    fun solve(game: KnightBattle): Location
}
