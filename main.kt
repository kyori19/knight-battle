import games.KnightBattle
import games.utils.Player
import games.utils.size
import solvers.MC
import solvers.Manual
import solvers.Solver
import solvers.UCT

fun main() {
    println("Player 1 can win? -> ${playerOneCanWin(KnightBattle(5.size))}")

    println("200 times UCT v.s. MC")
    val (first, second) = measureRatio(KnightBattle(10.size), UCT(100, 0.8) to MC(100))
    println("UCT won ${(first + second) / 2}% (First $first%, Second $second%)")

    val isCI = System.getenv("CI") == "true"
    if (!isCI) {
        val sample = playWithSolvers(KnightBattle(8.size), Manual, UCT(1000, 0.7))
        println("Manual v.s. MC -> $sample")
    }
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

fun measureRatio(game: KnightBattle, solvers: Pair<Solver, Solver>): Pair<Int, Int> =
    (0 until 100).count { playWithSolvers(game, solvers.first, solvers.second).winner == Player.PLAYER_ONE } to
            (0 until 100).count { playWithSolvers(game, solvers.second, solvers.first).winner == Player.PLAYER_TWO }
