package dix.ast.expressions

import dix.objects.DixObj
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject


interface ExprBuilder : Expression<DixObj<*>> {
    companion object {
        fun buildIfBuildable(expr: Expression<*>) =
                if (expr is ExprBuilder) expr.build()
                else expr

    }

    fun build(): Expression<*>
}
