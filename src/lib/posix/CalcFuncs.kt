package lib.posix
import lib.posix.movement.SimpleMovementSequence
import lib.posix.movement.SimpleMovementStruct
import kotlin.*
import kotlin.math.atan2
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.sqrt
import kotlin.math.pow

const val PI = Math.PI
const val TAU = PI * 2

var useDegrees : Boolean = false

/**
 * Causes Posix to use degrees as angles
 */
fun useDegrees() {
	useDegrees = true
}

/**
 * Cases Posix to use radians as angles
 * This is the case by default
 */
fun useRadians() {
	useDegrees = false
}

/**
 * Converts the selected angle unit to radians
 */
fun convAng(angle : Double) : Double {
	return if (useDegrees) {
		angle * 360 / TAU
	}
	else
	{
		angle
	}
}

/**
 * Converts radians to the selected angle unit
 */
fun angConv(angle : Double) : Double {
	return if (useDegrees) {
		angle * TAU / 360
	}
	else
	{
		angle
	}
}

/**
 * Calculates all the rotation angles for a quad omni-directional wheel setup for a single strafe
 * The line may be at an angle according to turnRight
 * The wheels' angles are returned in order: back left, back right, front left, front right, time
 * The angle on the wheels is:
 * Front left / back right: front left to back right
 * Front right / back left: front right to back left
 * If this is in reverse, arg right must be inverted to compensate
 */
fun strafeQuadOmni(forward: Double, right: Double, turnRight: Double, radius: Double, leftRightSpacing : Double, time : Double) : SimpleMovementStruct {
	val radialOffset : Double = convAng(turnRight) / TAU * (leftRightSpacing / 2 * PI)
	val rotDist : Double = radius * PI
	val backLeft : Double = angConv((forward + radialOffset - right) / rotDist * TAU)
	val backRight : Double = angConv((forward + radialOffset + right) / rotDist * TAU)
	val frontLeft : Double = angConv((forward - radialOffset + right) / rotDist * TAU)
	val frontRight : Double = angConv((forward - radialOffset - right) / rotDist * TAU)
	val mArr = arrayOf(backLeft, backRight, frontLeft, frontRight)
	return SimpleMovementStruct(time, *mArr.toDoubleArray())
}

/**
 * Calculates the movement sequence for a quad-omni-directional wheel setup for a signle strafe in a straight line
 * The line will remain straight while turning
 * The wheels' angles are returned in order: back left, back right, front left, front right
 * The angle on the wheels is:
 * Front left / back right: front left to back right
 * Front right / back left: front right to back left
 * If this is in reverse, arg right must be inverted to compensate
 */
fun straightQuadOmni(forward: Double, right: Double, turnRight: Double, radius: Double, leftRightSpacing : Double, time : Double, iterations: Int) : SimpleMovementSequence {
	var angOffs = angConv(atan2(-forward, right))
	val dist = sqrt(forward * forward + right * right)
	val partRotate = turnRight / iterations
	val mTime = time / iterations
	return SimpleMovementSequence(*Array(iterations) {
		val mAng: Double = convAng(angOffs + partRotate / 2)
		val mRight: Double = cos(mAng) * dist
		val mForward: Double = -sin(mAng) * dist
		val ars: SimpleMovementStruct = strafeQuadOmni(mForward, mRight, partRotate, radius, leftRightSpacing, mTime)
		angOffs -= partRotate
		ars
	})
}

fun estimateFallTime(height: Double, mult : Double, exponent : Double, termVelocity : Double) : Double {
	if (mult == 0.0 && termVelocity == 0.0) {
		return height.pow(1.0 / exponent)
	}
	return 0.0
}
