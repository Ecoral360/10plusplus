package dix.objects

import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject

class DixLazyExpr(expr: Expression<*>) : ASCObject<Expression<*>>(expr) {
    fun eval(): ASCObject<*> = value.eval()

    override fun toString(): String = value.toString()
}
