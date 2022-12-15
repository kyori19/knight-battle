import games.KnightBattle
import games.utils.Location
import games.utils.size

fun main() {
    var game = KnightBattle(5.size)
    while (game.winner == null) {
        println(game)
        println("Next: ${game.nextPlayer}")
        val can = game.candidates
        if (can != null) {
            println("Candidates: $can")
        }

        val loc = readln().split(" ")
            .map(String::toInt)
            .let { game.location(it.first(), it.last()) }
        game = game.move(loc)
    }
    println(game)
}

fun KnightBattle.location(x: Int, y: Int) = Location(size, x, y)
