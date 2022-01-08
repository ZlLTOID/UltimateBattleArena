package battleArena

import java.lang.StringBuilder

class Mage(name: String, hp: Int, attack: Int, defend: Int, dice: Dice, mana: Int, magicAttack: Int) :
    Warrior(name, hp, attack, defend, dice) {
    private val mana: Int
    private var currentMana: Int = mana
    private val magicAttack: Int

    init {
        this.mana = mana
        this.magicAttack = magicAttack
    }

    fun graphicMana(): String {
        val builder = StringBuilder()
        var manaBarParts: Int = 10
        var currentManaPercentage: Int = ((currentMana.toDouble() / mana) * 100).toInt()

        builder.append("[")
        for (i in 1..Math.round(currentManaPercentage / 10.0) * 10 / manaBarParts) {
            builder.append("#")
        }
        for (i in 1..manaBarParts - (Math.round(currentManaPercentage / 10.0) * 10 / manaBarParts)) {
            builder.append("_")
        }
        builder.append("]")

        var manaBar = builder.toString()

        return manaBar
    }

    override fun attack(enemy: Warrior) {
        var magicAttack = attack + (dice.roll() * 3)

        if (currentMana == mana) {
//            enemy.defend(magicAttack)
            enemy.setIncomingAttack(magicAttack)
            battleMessage = "$name attacks $enemy with magic and dealt $magicAttack damage!"
            currentMana = 0
        } else {
            super.attack(enemy)
            currentMana += 25

            if (currentMana > mana) {
                currentMana = mana
            }
        }
    }

    override fun defend() {
        val damage = incomingAttack - defend + (dice.roll() - 6)

        if (damage > 0) {
            currentHp -= damage

            battleMessage = "$name has lost $damage hp!"
            if (currentHp < 1) {
                battleMessage += " And died..."
            }

            if (currentHp <= 0) {
                currentHp = 0
            }
        } else {
            battleMessage = "$name defended himself!"
        }
    }
}