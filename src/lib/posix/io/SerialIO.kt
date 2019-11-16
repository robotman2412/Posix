package lib.posix.io

import jssc.SerialPort

open class ComPortIOChannel(mCom : String) {
    val com : String = mCom
    val jSerialPort : SerialPort = SerialPort(com)
}

open class GCodeIOChannel(mChannel : ComPortIOChannel) {
    val channel : ComPortIOChannel = mChannel
}