import games.KnightBattle
import games.utils.Player
import games.utils.size
import solvers.MC
import solvers.Manual
import solvers.Solver

fun main() {
    val game5 = KnightBattle(5.size)
    println("Player 1 can win? -> ${playerOneCanWin(game5)}")

    val isCI = System.getenv("CI") == "true"
    val game = playWithSolvers(game5, if (isCI) { MC(100) } else { Manual }, MC(100))
    println("${if (isCI) { "MC" } else { "Manual" }} v.s. MC -> $game")
}

fun playerOneCanWin(game: KnightBattle): Boolean =
    if (game.winner == null) {
        when (game.nextPlayer) {
            Player.PLAYER_ONE -> game.candidates.any { playerOneCanWin(game.move(it)) }
            Player.PLAYER_TWO -> game.candidates.all { playerOneCanWin(game.move(it)) }
        }
    } else {
        game.winner == Player.PLAYER_ONE
    }

fun playWithSolvers(initial: KnightBattle, player1: Solver, player2: Solver): KnightBattle {
    var game = initial
    while (game.winner == null) {
        val solver = when (game.nextPlayer) {
            Player.PLAYER_ONE -> player1
            Player.PLAYER_TWO -> player2
        }
        game = game.move(solver.solve(game))
    }
    return game
}
