package pokemon

import ru.ifmo.se.pokemon.*


fun main() {
    val battle = Battle()

    val facade = CustomAttack(
        Type.DARK, 70, 100, "Facade",
        listOf(Effect().condition(Status.BURN).stat(Stat.ATTACK, (70 * 2)),
            Effect().condition(Status.POISON).stat(Stat.ATTACK, (70 * 2)),
            Effect().condition(Status.PARALYZE).stat(Stat.ATTACK, (70 * 2)))
    )
    val zenHeadbutt = CustomAttack(Type.PSYCHIC, 80, 90, "Zen Headbutt")
    val calmMind = CustomAttack(Type.PSYCHIC, 0, 0, "Calm Mind",
        selfActions = { it.setMod(Stat.SPECIAL_ATTACK, 1); it.setMod(Stat.SPECIAL_DEFENSE, 1) }
    )
    val doubleTeam = CustomAttack(Type.NORMAL, 0, 100, "Double Team",
        selfActions = { it.setMod(Stat.EVASION, 1) }
    )
    val tackle = CustomAttack(Type.NORMAL, 40, 100, "Tackle")
    val sandAttack = CustomAttack(Type.GROUND, 0, 100, "Sand Attack",
        opponentActions = { it.setMod(Stat.ACCURACY, -1) }
    )
    val psychic = CustomAttack(Type.PSYCHIC, 90, 100, "Psychic",
        opponentActions = { it.setMod(Stat.SPECIAL_DEFENSE, -1) }
    )
    val chargeBeam = CustomAttack(Type.ELECTRIC, 50, 90, "Charge Beam",
        opponentActions = { chance(70) { it.setMod(Stat.SPECIAL_ATTACK, 1) } }
    )
    val wildCharge = CustomAttack(Type.ELECTRIC, 90, 100, "Wild Charge",
        opponentActions = { chance(30) { it.setMod(Stat.SPEED, -1) } }
    )
    val crunch = CustomAttack(Type.DARK, 80, 100, "Crunch",
        opponentActions = { chance(20) { it.setMod(Stat.DEFENSE, -1) } }
    )
    val stringShot = CustomAttack(Type.BUG, 0, 95, "String Shot",
        opponentActions = { it.setMod(Stat.SPEED, -2) }
    )

    val sableye = Spok("Sableye", 1).apply {
        setStatsFromArgs(50.0, 75.0, 75.0, 65.0, 65.0, 50.0)
        addTypes(Type.DARK, Type.GHOST)
        addMoves(facade, zenHeadbutt, calmMind, doubleTeam)
    }
    val eeve = Spok("Eevee", 1).apply {
        setStatsFromArgs(55.0, 55.0, 50.0, 45.0, 65.0, 55.0)
        addTypes(Type.NORMAL)
        addMoves(tackle, facade, sandAttack)
    }
    val umbreon = Spok("Umbreon", 1).apply {
        setStatsFromArgs(95.0, 65.0, 110.0, 60.0, 130.0, 65.0)
        addTypes(Type.DARK)
        addMoves(tackle, facade, sandAttack, psychic)
    }
    val grubbin = Spok("Grubbin", 1).apply {
        setStatsFromArgs(47.0, 62.0, 45.0, 55.0, 45.0, 46.0)
        addTypes(Type.BUG)
        addMoves(chargeBeam, wildCharge)
    }
    val charjabug = Spok("Charjabug", 1).apply {
        setStatsFromArgs(57.0, 82.0, 95.0, 55.0, 75.0, 36.0)
        addTypes(Type.BUG, Type.ELECTRIC)
        addMoves(chargeBeam, wildCharge, crunch)
    }
    val vikavolt = Spok("Vikavolt", 1).apply {
        setStatsFromArgs(77.0, 70.0, 90.0, 145.0, 75.0, 43.0)
        addTypes(Type.BUG, Type.ELECTRIC)
        addMoves(chargeBeam, wildCharge, crunch, stringShot)
    }

    battle.addAllies(sableye, eeve, umbreon)
    battle.addFoes(grubbin, charjabug, vikavolt)

    battle.go()
}
