package dix.ast.expressions

import dix.objects.DixFloat
import dix.objects.DixInt
import dix.objects.DixNumber
import dix.objects.DixString
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject
import org.ascore.lang.objects.containsType
import kotlin.math.floor

private operator fun Number.plus(value: Number): Double = this.toDouble() + value.toDouble()
private fun Double.isInt(): Boolean = floor(this) == this

data class AddExpr(private val left: Expression<*>, private val right: Expression<*>) : Expression<ASCObject<*>> {
    override fun eval(): ASCObject<*> {
        val leftValue = left.eval()
        val rightValue = right.eval()
        return when {
            leftValue.containsType<String>() || rightValue.containsType<String>() -> {
                DixString("" + leftValue.value + rightValue.value)
            }

            leftValue.containsType<Number>() && rightValue.containsType<Number>() -> {
                val result = (leftValue.value!! as Number) + (rightValue.value!! as Number)
                if (result.isInt()) DixInt(result.toInt()) else DixFloat(result)
            }

            else -> throw ArithmeticException("Addition not supported for '${leftValue.javaClass.simpleName}' and '${rightValue.javaClass.simpleName}'")
        }
    }
}
