package dix.objects

import org.ascore.ast.buildingBlocs.Expression

class DixLazyExpr(val expr: Expression<*>) : DixObj<DixObj<*>>(NoValue) {
    companion object {
        fun evalIfLazy(value: DixObj<*>) =
                if (value is DixLazyExpr) value.eval()
                else value

    }

    fun eval(): DixObj<*> = expr.eval() as DixObj<*>

    override fun getValue(): DixObj<*> = this

    // override fun toString(): String = eval().toString()
}
