package dix.ast.expressions

import dix.execution.DixExecutorState
import dix.objects.DixFunctionModule
import dix.objects.DixObj
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject

class FuncCallExpr(val func: VarExpr, val args: List<Expression<*>>, val executorState: DixExecutorState) : Expression<ASCObject<*>> {

    override fun eval(): ASCObject<*> {
        val f = func.eval() as DixFunctionModule
        return f.call(args.map { it.eval() as DixObj<*> })
    }
}


class FuncCallExprBuilder(private val func: VarExpr, val executorState: DixExecutorState, args: ArrayList<Expression<*>> = arrayListOf()) : ExprBuilder {
    private val args: ArrayList<Expression<*>>

    init {
        this.args = args
    }

    override fun eval(): DixObj<*> {
        TODO("Not yet implemented")
    }

    fun addExpr(expr: Expression<*>) {
        args.add(if (expr is FuncCallExprBuilder) expr.build() else expr)
    }

    override fun build(): FuncCallExpr = FuncCallExpr(func, args, executorState)
}