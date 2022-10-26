package pokemon

import ru.ifmo.se.pokemon.Effect
import ru.ifmo.se.pokemon.PhysicalMove
import ru.ifmo.se.pokemon.Pokemon
import ru.ifmo.se.pokemon.Type

open class CustomAttack(
    type: Type,
    power: Number,
    accuracy: Number,
    desc: String = "",
    private val selfEffects: List<Effect> = emptyList(),
    private val opponentEffects: List<Effect> = emptyList(),
    private val selfActions: (Pokemon) -> Unit = {},
    private val opponentActions: (Pokemon) -> Unit = {},
) :
    PhysicalMove(type, power.toDouble(), (accuracy.toDouble() / 100)) {

    override fun applySelfEffects(p0: Pokemon?) {
        super.applySelfEffects(p0)
        selfEffects.forEach { p0?.addEffect(it) }
        selfActions(p0!!)
    }

    override fun applyOppEffects(p0: Pokemon?) {
        super.applySelfEffects(p0)
        opponentEffects.forEach { p0?.addEffect(it) }
        opponentActions(p0!!)
    }

    private var description: String = "использовал $desc"
    override fun describe() = description
    fun setDescription(desc: String) {
        description = desc
    }
}
