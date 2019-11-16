package lib.posix.movement

open class SimpleMovementSequence(vararg mSequence : SimpleMovementStruct) {
    open var sequence : Array<out SimpleMovementStruct> = mSequence
}

open class SimpleMovementStruct(mTime : Double, vararg mMovements : Double) {
    open var movements : Array<Double> = mMovements.toTypedArray()
    open var time : Double = mTime
    open fun toSequence() : SimpleMovementSequence {
        return SimpleMovementSequence(this)
    }
}
