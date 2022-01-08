package battleArena

fun main(arg: Array<String>) {
    val dice = Dice(12)

    println("Enter name for your Warrior")
    val warriorName: String = (readLine()?.ifBlank { null } ?: "Ziltoid").toString()

    val enemy: EnemyWarrior = EnemyWarrior("Conan", 80, dice.roll() * 2, dice.roll(), dice)
    val warrior: Warrior = Warrior(warriorName, 80, dice.roll() * 2, dice.roll(), dice)
//    val warrior: Mage = Mage(warriorName, warriorHp, warriorAttack, warriorDefend, dice, 100, 30)
    val arena: Arena = Arena(enemy, warrior, dice)

    arena.fight()
}