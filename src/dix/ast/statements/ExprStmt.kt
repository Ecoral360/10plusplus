package dix.ast.statements

import dix.ast.expressions.ExprBuilder
import dix.ast.expressions.FuncCallExprBuilder
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.ast.buildingBlocs.Statement

/**
 * Class representing a print statement. It takes an [Expression] as argument and prints it when executed at runtime.
 */
class ExprStmt(private val expression: Expression<*>) : Statement() {

    /**
     * Method called at runtime that executes the print statement, thus printing the expression.
     */
    override fun execute(): Any? {
        if (expression is ExprBuilder) expression.build().eval()
        else expression.eval()
        return null
    }

    override fun toString(): String = expression.toString()
}
