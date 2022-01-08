package battleArena

import java.lang.StringBuilder

open class Warrior(name: String, hp: Int, attack: Int, defend: Int, dice: Dice) {
    protected val name: String
    private var hp: Int
    protected var currentHp: Int = hp
    protected var attack: Int
    protected var defend: Int
    protected var dice: Dice
    protected var battleMessage: String = ""
    protected var incomingAttack: Int = 0
    protected open var itsInTurn: Boolean = true
    protected var state: WarriorState = WarriorState.DEFENDING
    protected val alive: Boolean
        get() {
            return currentHp > 0
        }

    init {
        this.name = name
        this.hp = hp
        this.attack = attack
        this.defend = defend
        this.dice = dice
    }

    open fun defend() {
        state = WarriorState.DEFENDING
        val damage = incomingAttack - (defend + dice.roll())

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

        if (!itsInTurn) itsInTurn = true else itsInTurn = false
    }

    open fun attack(enemy: Warrior) {
        state = WarriorState.ATTACKING
        val attack: Int = attack + dice.roll()

        enemy.incomingAttack = attack

        battleMessage = "$name attacks $enemy with $attack damage!"
        if (enemy.currentHp < 1) {
            battleMessage += " And killed Him!"
        }

        if (!itsInTurn) itsInTurn = true else itsInTurn = false
        state = WarriorState.DEFENDING
    }

    fun graphicHp(): String {
        val builder = StringBuilder()
        var healthBarParts: Int = 10
        var currentHpPercentage: Int = ((currentHp.toDouble() / hp) * 100).toInt()

        builder.append("[")
        for (i in 1..Math.round(currentHpPercentage / 10.0) * 10 / healthBarParts) {
            builder.append("#")
        }
        for (i in 1..healthBarParts - (Math.round(currentHpPercentage / 10.0) * 10 / healthBarParts)) {
            builder.append("_")
        }
        builder.append("]")

        var healthBar = builder.toString()

        return healthBar
    }

    fun getMessage(): String {
        return battleMessage
    }

    @JvmName("getState1")
    fun getState(): WarriorState {
        return this.state
    }

//    fun isInTurn(): Boolean
//    {
//        return itsInTurn
//    }

    // This method gives info about player's activity state in last turn
    fun wasInTurn(): Boolean
    {
        return !itsInTurn
    }

    fun isAlive(): Boolean {
        if (!alive) {
            battleMessage = "Warrior $name has died."
        }

        return currentHp > 0
    }

    @JvmName("setIncomingAttack1")
    fun setIncomingAttack(attack: Int) {
        this.incomingAttack = attack
    }

    override fun toString(): String {
        return name
    }
}