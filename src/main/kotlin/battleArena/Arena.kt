package battleArena

class Arena(enemyWarrior: EnemyWarrior, warrior: Warrior, dice: Dice) {
    private val enemyWarrior: EnemyWarrior
    private val warrior: Warrior
    private val dice: Dice

    init {
        this.enemyWarrior = enemyWarrior
        this.warrior = warrior
        this.dice = dice
    }

    fun fight() {
        println("Welcome to Arena")
        println("Today will fight warriors \u001b[31m $enemyWarrior \u001b[0m and \u001b[31m $warrior \u001b[0m")
        println("Let the fight begin...")

        while (enemyWarrior.isAlive() && warrior.isAlive()) {
            println("It\'s your turn, what is your move?")
            printOptions()
            var playersAction: String = (readLine()?.ifBlank { null } ?: "Conan").toString()
            playersResponse(playersAction)
            enemyResponse()

            println("Your enemy is ${enemyWarrior.getState()}, what is your move?")
            printOptions()
            playersAction = (readLine()?.ifBlank { null } ?: "Conan").toString()
            playersResponse(playersAction)
        }
    }

    fun printArena() {
        println("-------------- Arena --------------\n")
        println("Warrior\'s health: \n")
        println("$enemyWarrior \u001B[32m ${enemyWarrior.graphicHp()} \u001b[0m")

        if (warrior is Mage) {
            println("$warrior \u001B[32m ${warrior.graphicHp()} \u001B[0m | \u001B[34m ${warrior.graphicMana()} \u001B[0m")
        } else if (warrior is Warrior) {
            println("$warrior \u001B[32m ${warrior.graphicHp()} \u001B[0m")
        }
    }

    fun printMessage(message: String) {
        println(message)
        Thread.sleep(200)
    }

    fun printInfoAboutBattle() {
        printArena()
        if (warrior.wasInTurn()) {
            printMessage(warrior.getMessage())
            printMessage(enemyWarrior.getMessage())
        } else {
            printMessage(enemyWarrior.getMessage())
            printMessage(warrior.getMessage())
        }
    }

    fun printOptions() {
        println("A: Attack")
        println("B: Defend")
    }

    fun playersResponse(playerAction: String) {
        if (playerAction == "A") {
            warrior.attack(enemyWarrior)
            printInfoAboutBattle()
        } else if (playerAction !== "B") {
            warrior.defend()
            printInfoAboutBattle()
        } else {
            warrior.defend()
            printInfoAboutBattle()
        }
    }

    fun enemyResponse() {
        enemyWarrior.act(warrior)
        printInfoAboutBattle()
    }
}