package lib.posix

fun hasTheLargeHadronColliderDestroyedTheWorldYet() : Boolean {
    return false
}

fun isMyComputerCurrentlyPowerenOn() : Boolean {
    return true
}

/**
 * Stitches together all the movement sequences provided
 * It does not matter what information this contains
 */
fun stitchMoves(vararg args : Any) : Array<out Array<Double>>? {
    return Posix.stitchMovements(*args)
}
