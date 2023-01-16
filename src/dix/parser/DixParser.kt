package dix.parser

import dix.ast.expressions.*
import dix.ast.statements.ExprStmt
import dix.execution.DixExecutorState
import dix.lexer.DixLexer
import dix.objects.*
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.errors.ASCErrors
import org.ascore.executor.ASCExecutor
import org.ascore.generators.ast.AstGenerator
import org.ascore.tokens.Token

/**
 * The parser for the Dix language.
 *
 * This parser is responsible for defining the rules for parsing the Dix language. The actual parsing is done by the
 * [AstGenerator] class in accordance with the rules defined in this class.
 *
 *  * Edit the [addExpressions] method to add new expressions to the language.
 *  * Edit the [addStatements] method to add new statements to the language.
 *
 *  @param executorInstance the executor instance to use for executing the AST
 */
class DixParser(executorInstance: ASCExecutor<DixExecutorState>) : AstGenerator<DixAstFrameKind>() {
    private val executorInstance: ASCExecutor<DixExecutorState>

    init {
        setPatternProccessor(executorInstance.getLexer<DixLexer>()::remplaceCategoriesByMembers)
        defineAstFrame(DixAstFrameKind.DEFAULT)
        addStatements()
        addExpressions()
        pushAstFrame(DixAstFrameKind.DEFAULT)
        this.executorInstance = executorInstance
    }

    /**
     * Defines the rules of the statements of the language.
     */
    private fun addStatements() {
        // add your statements here
        addStatement("expression") { p: List<Any> -> ExprStmt(p[0] as Expression<*>) }
    }

    /**
     * Defines the rules of the expressions of the language.
     */
    private fun addExpressions() {
        // add your expressions here
        addExpression("PAREN_OPEN #expression PAREN_CLOSE") { p: List<Any> ->
            evalOneExpr(arrayListOf(p.subList(1, p.size - 1)), null)
        }

        addExpression("{datatypes}~VARIABLE") { p ->
            val token = p[0] as Token
            when (token.name) {
                "STRING" -> ConstValueExpr(DixString(token))
                "NUMBER" -> ConstValueExpr(DixNumber(token))
                "CHAR" -> ConstValueExpr(DixChar(token))
                "VARIABLE" -> {
                    VarExpr(token.value, executorInstance.executorState)
                }
                else -> throw RuntimeException()
            }
        }

        addExpression("expression expression") { p: List<Any> ->
            val expr1 = p[0] as Expression<*>
            val expr2 = p[1] as Expression<*>
            when (expr1) {
                is FuncCallExprBuilder -> {
                    expr1.addExpr(expr2)
                    expr1
                }

                is VarExpr -> FuncCallExprBuilder(expr1, executorInstance.executorState, arrayListOf(expr2))
                else -> throw ASCErrors.ErreurSyntaxe("AAAAAA")
            }
        }
    }
}
