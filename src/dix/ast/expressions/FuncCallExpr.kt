package dix.ast.expressions

import dix.execution.DixExecutorState
import dix.objects.DixFunctionModule
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject

class FuncCallExpr(val func: VarExpr, val args: List<Expression<*>>, val executorState: DixExecutorState) : Expression<ASCObject<*>> {

    override fun eval(): ASCObject<*> {
        val f = func.eval().ascObject as DixFunctionModule
        return f.call(args.map { it.eval() })
    }
}


class FuncCallExprBuilder(private val func: VarExpr, val executorState: DixExecutorState, args: ArrayList<Expression<*>> = arrayListOf()) : Expression<ASCObject<*>> {
    private val args: ArrayList<Expression<*>>

    init {
        this.args = args
    }

    override fun eval(): ASCObject<*> {
        TODO("Not yet implemented")
    }

    fun addExpr(expr: Expression<*>) {
        args.add(if (expr is FuncCallExprBuilder) expr.build() else expr)
    }

    fun build(): FuncCallExpr = FuncCallExpr(func, args, executorState)
}