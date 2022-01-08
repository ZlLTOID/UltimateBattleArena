package battleArena

enum class WarriorState {
//    WAITING {
//        override fun toString(): String {
//            return "Waiting"
//        }
//            },
    DEFENDING {
        override fun toString(): String {
            return "Defending"
        }
              },
    ATTACKING {
        override fun toString(): String {
            return "Attacking"
        }
    }
}