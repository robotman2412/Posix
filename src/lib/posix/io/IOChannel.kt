package lib.posix.io

import lib.posix.movement.SimpleMovementSequence
import lib.posix.movement.SimpleMovementStruct

interface SimpleIOChannel {
    fun setTimeMult(mult : Double)
    fun getTimeMult() : Double
    fun enact(seq : SimpleMovementSequence)
    fun enact(mov : SimpleMovementStruct)
    fun getSensors() : Array<Any>
    fun getModelID() : String
}