package pokemon

import ru.ifmo.se.pokemon.Battle
import ru.ifmo.se.pokemon.Pokemon

fun Battle.addAllies(vararg allies: Pokemon) {
    allies.forEach { addAlly(it) }
}
fun Battle.addFoes(vararg allies: Pokemon) {
    allies.forEach { addFoe(it) }
}

fun chance(percent: Int): Boolean {
    return Math.random() < (percent / 100)
}
fun chance(percent: Int, action: () -> Unit): Boolean {
    return Math.random() < (percent / 100)
}
