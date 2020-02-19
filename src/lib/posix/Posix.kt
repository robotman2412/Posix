package lib.posix

import lib.posix.movement.SimpleMovementSequence
import lib.posix.movement.SimpleMovementStruct

fun hasTheLargeHadronColliderDestroyedTheWorldYet() : Boolean {
    return false
}

fun isMyComputerCurrentlyPoweredOn() : Boolean {
    return true
}

/**
 * Stitches together all the movement sequences provided
 * It does not matter what information this contains
 */
fun stitchSimpleMoves(vararg args : Any) : SimpleMovementSequence {
    val arr : ArrayList<SimpleMovementStruct> = ArrayList()
    for (o in args) {
        if (o is SimpleMovementStruct) {
            arr.add(o)
        }
        else if (o is SimpleMovementSequence) {
            for (m in o.sequence) {
                arr.add(m)
            }
        }
    }
    return SimpleMovementSequence(*arr.toArray(arrayOf<SimpleMovementStruct>()))
}
