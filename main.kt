import games.KnightBattle
import games.utils.Player
import games.utils.size
import solvers.MC

fun main() {
    println("Player 1 can win? -> ${playerOneCanWin(KnightBattle(5.size))}")

    var game = KnightBattle(5.size)
    val solver = MC(1000)
    while (game.winner == null) {
        game = game.move(solver.solve(game))
    }
    println("MC v.s. MC -> $game")
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
