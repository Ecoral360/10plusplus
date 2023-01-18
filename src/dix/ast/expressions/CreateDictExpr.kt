package dix.ast.expressions

import dix.objects.DixDict
import dix.objects.DixList
import dix.objects.DixObj
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.errors.ASCErrors
import org.ascore.lang.objects.ASCObject


data class CreateDictExpr(private val values: Map<Expression<*>, Expression<*>> = mapOf()) : Expression<DixDict> {

    override fun eval(): DixDict {
        val pairs = values.map { (key, value) -> key.eval() as DixObj<*> to value.eval() as DixObj<*> }
        return DixDict(mapOf(*pairs.toTypedArray()))
    }
}

class CreateDictBuilderExpr(private val elements: ArrayList<Expression<*>> = arrayListOf()) : ExprBuilder {

    fun addExpr(expr: Expression<*>) {
        elements.add(if (expr is ExprBuilder) expr.build() else expr)
    }

    override fun eval(): DixObj<*> {
        TODO("Not yet implemented")
    }

    override fun build(): CreateDictExpr {
        if (elements.size % 2 != 0) {
            throw ASCErrors.ErreurType("Tried to create a dict with an odd number of elements in it.")
        }
        return CreateDictExpr(mapOf(*elements.chunked(2) { (key, value) -> key to value }.toTypedArray()))
    }
}
