package lib.posix.models

import lib.posix.*
import lib.posix.movement.SimpleMovementSequence
import lib.posix.movement.SimpleMovementStruct
import kotlin.math.round
import kotlin.math.sqrt

open class QuadOmniWheel(mWheelRadius : Double, mLeftRightDist : Double, mInterpolateUnits : Double, mUseDeg : Boolean) {
    open var useDeg = mUseDeg
    open var leftRightDist = mLeftRightDist
    open var wheelRadius = mWheelRadius
    open var interpolateUnits = mInterpolateUnits
    open var movementChannel : Any = 0 //m8ke this
    fun calcStrafe(forward : Double, right : Double, turnRight : Double, millis : Double) : SimpleMovementStruct {
        val pUseDeg = useDegrees
        useDegrees = useDeg
        val ret = strafeQuadOmni(forward, right, turnRight, wheelRadius, leftRightDist, millis)
        useDegrees = pUseDeg
        return ret
    }
    fun calcStaight(forward : Double, right : Double, turnRight : Double, millis : Double) : SimpleMovementSequence {
        val pUseDeg = useDegrees
        useDegrees = useDeg
        val dist = sqrt(right * right + forward * forward)
        val ret = straightQuadOmni(forward, right, turnRight, wheelRadius, leftRightDist, millis, round(dist / interpolateUnits).toInt())
        useDegrees = pUseDeg
        return ret
    }
}
