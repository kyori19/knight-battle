package solvers

import games.KnightBattle
import games.utils.Location

object Manual : Solver {
    override fun solve(game: KnightBattle): Location {
        println(game)
        println("${game.nextPlayer}'s turn")
        println("Candidates: ${game.candidates}")
        println("Type your move (x y)")
        return readln().split(" ").map(String::toInt)
            .let { game.Location(it.first(), it.last()) }
    }
}
