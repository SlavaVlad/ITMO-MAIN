package pokemon

import ru.ifmo.se.pokemon.Move
import ru.ifmo.se.pokemon.Pokemon
import ru.ifmo.se.pokemon.Stat
import ru.ifmo.se.pokemon.Type

class Spok (
    name: String,
    level: Int = 1,
    types: List<Type> = emptyList(),
    stats: Map<Stat, Double> = mapOf(),
    moves: List<Move> = listOf(Move.getStruggleMove())
) : Pokemon(name, level) {

    init {
        super.setLevel(level)
        super.setType(*types.toTypedArray())
        if (stats.isNotEmpty()) { setStatsFromMap(stats) }
        moves.forEach { addMove(it) }
    }

    fun setStatsFromArgs(
        hp: Double,
        attack: Double,
        defense: Double,
        specialAttack: Double,
        specialDefense: Double,
        speed: Double
    ) {
        super.setStats(hp, attack, defense, specialAttack, specialDefense, speed)
    }

    private fun setStatsFromMap(stats: Map<Stat, Double>) {
        try {
            super.setStats(
                stats[Stat.HP]!!,
                stats[Stat.ATTACK]!!,
                stats[Stat.DEFENSE]!!,
                stats[Stat.SPECIAL_ATTACK]!!,
                stats[Stat.SPECIAL_DEFENSE]!!,
                stats[Stat.SPEED]!!,
            )
        } catch (npe: NullPointerException) {
            throw Exception("Stats Set Failed")
        }
    }

    fun addTypes(vararg types: Type) {
        super.setType(*types)
    }

    fun addMoves(vararg moves: Move) {
        moves.forEach { addMove(it) }
    }
}