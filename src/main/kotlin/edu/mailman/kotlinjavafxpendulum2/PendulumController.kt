package edu.mailman.kotlinjavafxpendulum2

import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.collections.ObservableList
import javafx.geometry.Point2D
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Polyline
import javafx.util.Duration
import kotlin.math.cos
import kotlin.math.sin

const val xOffset = 250
const val yOffset = 50.0

const val pendulumLength = 400.0
const val startAngle = 252.0

const val forward = 1
const val reverse = -1

val angleIncrements = arrayOf(0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0,
    4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.5)

class PendulumController {
    lateinit var polyLineArc: Polyline
    lateinit var cirPendulumBall: Circle
    lateinit var linPendulumString: Line

    private var points: MutableList<Point2D> = ArrayList()
    private var pointIndex = -1
    private var pointDirection = forward

    fun initialize() {
        // Set up a timer
        val timeline =
            Timeline(KeyFrame(Duration.seconds(0.2), {animate()}))
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()

        // Build a polyline of points forming an arc
        val listOfPoints: ObservableList<Double> = polyLineArc.points
        var currentAngle = startAngle
        for (angleIncrement in angleIncrements) {
            currentAngle += angleIncrement
            println(currentAngle)

            // Calculate the coordinates of the next point
            val x = pendulumLength *
                    cos(Math.toRadians((currentAngle)))
            val y = pendulumLength *
                    sin(Math.toRadians((currentAngle)))

            // Calculate the offset location of the point
            val dataPoint = offset(x, y)

            // Add the point to the arraylist and the polyline
            points.add(dataPoint)
            listOfPoints.addAll(dataPoint.x, dataPoint.y)
        }

        for (point in points) {
            println(point)
        }
    }
    private fun offset(x: Double, y: Double): Point2D {
        // Return a 2D point offset from the given coordinate
        val xOffset = x + xOffset
        val yOffset = -y + yOffset
        return Point2D(xOffset, yOffset)
    }

    private fun animate() {
        // Increment the point in the correct direction
        pointIndex += pointDirection

        // Check if this is the beginning or end of the arc
        if (pointIndex == points.size) {
            pointIndex -= 2
            pointDirection = reverse
        }
        else if (pointIndex == 0)
            pointDirection = forward

        // Move the ball along the arc
        cirPendulumBall.centerX = points[pointIndex].x
        cirPendulumBall.centerY = points[pointIndex].y

        // Move the end of the pendulum's string along the arc
        linPendulumString.endX = points[pointIndex].x
        linPendulumString.endY = points[pointIndex].y
    }
}