package games.utils

enum class Player {
    PLAYER_ONE,
    PLAYER_TWO,
    ;

    operator fun not() = when (this) {
        PLAYER_ONE -> PLAYER_TWO
        PLAYER_TWO -> PLAYER_ONE
    }
}
