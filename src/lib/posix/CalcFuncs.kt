package lib.posix
import kotlin.*
import kotlin.math.atan2
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.sqrt

const val PI = Math.PI
const val TAU = PI * 2

/**
 * Calculates all the rotation angles for a quad omni-directional wheel setup for a single strafe
 * The line may be at an angle according to turnRight
 * The wheels' angles are returned in order: back left, back right, front left, front right
 * The angle on the wheels is:
 * Front left / back right: front left to back right
 * Front right / back left: front right to back left
 * If this is in reverse, arg right must be inverted to compensate
 * The returned angle is in radians, so is turnRight
 */
fun strafeQuadOmni(forward: Double, right: Double, turnRight: Double, radius: Double, leftRightSpacing : Double) : Array<Double> {
	val radialOffset : Double = turnRight / TAU * (leftRightSpacing / 2 * PI)
	val rotDist : Double = radius * PI
	val backLeft : Double = (forward + radialOffset - right) / rotDist * TAU
	val backRight : Double = (forward + radialOffset + right) / rotDist * TAU
	val frontLeft : Double = (forward - radialOffset + right) / rotDist * TAU
	val frontRight : Double = (forward - radialOffset - right) / rotDist * TAU
	return arrayOf(backLeft, backRight, frontLeft, frontRight)
}

/**
 * Calculates the movement sequence for a quad-omni-directional wheel setup for a signle strafe in a straight line
 * The line will remain straight while turning
 * The wheels' angles are returned in order: back left, back right, front left, front right
 * The angle on the wheels is:
 * Front left / back right: front left to back right
 * Front right / back left: front right to back left
 * If this is in reverse, arg right must be inverted to compensate
 * The returned angle is in radians, so is turnRight
 */
fun straightQuadOmni(forward: Double, right: Double, turnRight: Double, radius: Double, leftRightSpacing : Double, iterations: Int) : Array<Array<Double>> {
	var angOffs = atan2(-forward, right)
	val dist = sqrt(forward * forward + right * right)
	val partRotate = turnRight / iterations
	return Array(iterations) {
		val mAng : Double = angOffs + partRotate / 2
		val mRight : Double = cos(mAng) * dist
		val mForward : Double = sin(mAng) * dist
		val ars : Array<Double> = strafeQuadOmni(mForward, mRight, partRotate, radius, leftRightSpacing)
		angOffs -= partRotate
		ars
	}
}
