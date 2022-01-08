package battleArena

class EnemyWarrior(name: String, hp: Int, attack: Int, defend: Int, dice: Dice) :
    Warrior(name, hp, attack, defend, dice) {
    override var itsInTurn: Boolean = false

    init {
        this.itsInTurn = false
    }

    private fun battleBehavior(enemy: Warrior) {
        if (currentHp > currentHp/100*80) {
            if (enemy.getState() == WarriorState.DEFENDING) {
                attack(enemy)
            } else if (enemy.getState() == WarriorState.ATTACKING) {
                defend()
            }
        } else if(currentHp < currentHp/100*80) {
            if (enemy.getState() == WarriorState.DEFENDING) {
                attack(enemy)
            } else if (enemy.getState() == WarriorState.ATTACKING) {
                defend()
            }
        }
    }

    fun act(enemy: Warrior) {
        battleBehavior(enemy)
        if (!itsInTurn) itsInTurn = true else itsInTurn = false
    }
}