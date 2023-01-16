package dix.ast.expressions

import dix.objects.DixList
import dix.objects.DixObj
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject


data class CreateListExpr(private val values: List<Expression<*>> = arrayListOf()) : Expression<DixList> {

    override fun eval(): DixList {
        return DixList(values.map { it.eval() as DixObj<*> }.toTypedArray())
    }
}

class CreateListBuilderExpr(private val elements: ArrayList<Expression<*>> = arrayListOf()) : ExprBuilder {

    fun addExpr(expr: Expression<*>) {
        elements.add(if (expr is CreateListBuilderExpr) expr.build() else expr)
    }

    override fun eval(): DixObj<*> {
        TODO("Not yet implemented")
    }

    override fun build(): CreateListExpr {
        return CreateListExpr(elements)
    }
}
