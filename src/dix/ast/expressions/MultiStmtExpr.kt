package dix.ast.expressions

import dix.objects.DixList
import dix.objects.DixObj
import org.ascore.ast.buildingBlocs.Expression


data class MultiStmtExpr(val expressions: ArrayList<Expression<*>>) : Expression<DixList> {

    fun addExpr(expression: Expression<*>) {
        expressions.add(expression)
    }

    override fun eval(): DixList {
        return DixList(expressions.map { it.eval() as DixObj }.toTypedArray())
    }
}
