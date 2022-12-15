import games.KnightBattle
import games.utils.Location
import games.utils.Player
import games.utils.size

fun main() {
    println("Player 1 can win? -> ${playerOneCanWin(KnightBattle(5.size, listOf(Location(5.size, 0, 0))))}")
}

fun playerOneCanWin(game: KnightBattle): Boolean =
    if (game.winner == null) {
        when (game.nextPlayer) {
            Player.PLAYER_ONE -> game.candidates!!.any { playerOneCanWin(game.move(it)) }
            Player.PLAYER_TWO -> game.candidates!!.all { playerOneCanWin(game.move(it)) }
        }
    } else {
        game.winner == Player.PLAYER_ONE
    }
