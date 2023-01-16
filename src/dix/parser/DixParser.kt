package dix.parser

import dix.ast.expressions.AddExpr
import dix.ast.expressions.ConstValueExpr
import dix.ast.statements.PrintStmt
import dix.execution.DixExecutorState
import dix.lexer.DixLexer
import dix.objects.DixFloat
import dix.objects.DixInt
import dix.objects.DixString
import org.ascore.ast.buildingBlocs.Expression
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
        addStatement("PRINT expression") { p: List<Any> -> PrintStmt(p[1] as Expression<*>) }
    }

    /**
     * Defines the rules of the expressions of the language.
     */
    private fun addExpressions() {
        // add your expressions here
        addExpression("{datatypes}") { p: List<Any> ->
            val token = p[0] as Token
            when (token.name()) {
                "INT" -> ConstValueExpr(DixInt(token))
                "FLOAT" -> ConstValueExpr(DixFloat(token))
                "STRING" -> ConstValueExpr(DixString(token))
                else -> throw NoSuchElementException(token.name())
            }
        }
        addExpression("expression PLUS expression") { p: List<Any> ->
            AddExpr(
                p[0] as Expression<*>, p[2] as Expression<*>
            )
        }
    }
}
