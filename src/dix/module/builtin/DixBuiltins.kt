package dix.module.builtin

import dix.ast.expressions.LazyExpr
import dix.ast.expressions.VarExpr
import dix.execution.DixExecutorState
import dix.module.DixModule
import dix.objects.*
import org.ascore.lang.objects.ASCVariable

object DixBuiltins : DixModule {
    override fun loadFunctions(executorState: DixExecutorState): Array<DixFunctionModule> =
            arrayOf(
                    DixFunctionModule("print") { args ->
                        println(args.joinToString(" ") { DixLazyExpr.evalIfLazy(it).toString() })
                        DixObj.NoValue
                    },

                    DixFunctionModule("+") { args ->
                        DixNumber(args.sumOf { (it.value as Number).toDouble() })
                    },

                    DixFunctionModule("++") { args ->
                        DixNumber((args[0].value as Number).toDouble() + 1)
                    },

                    DixFunctionModule("<") { args ->
                        DixNumber((args[0].value as Number).toDouble() < (args[1].value as Number).toDouble())
                    },

                    DixFunctionModule("<=") { args ->
                        DixNumber((args[0].value as Number).toDouble() <= (args[1].value as Number).toDouble())
                    },

                    DixFunctionModule(">") { args ->
                        DixNumber((args[0].value as Number).toDouble() > (args[1].value as Number).toDouble())
                    },

                    DixFunctionModule(">=") { args ->
                        DixNumber((args[0].value as Number).toDouble() >= (args[1].value as Number).toDouble())
                    },

                    DixFunctionModule("set") { args ->
                        val variable = args[0] as DixLazyExpr
                        val varName = (variable.expr as VarExpr).name
                        val value = DixLazyExpr.evalIfLazy(args[1])
                        executorState.scopeManager.currentScopeInstance.getVariable(varName).ascObject = value
                        value
                    },

                    DixFunctionModule("if") { args ->
                        val condition = args[0]
                        val ifBody = args[1]
                        val elseBody = args.getOrNull(2)

                        if (DixLazyExpr.evalIfLazy(condition).boolValue()) {
                            DixLazyExpr.evalIfLazy(ifBody)
                        } else if (elseBody != null) {
                            DixLazyExpr.evalIfLazy(elseBody)
                        }

                        DixObj.NoValue
                    },

                    DixFunctionModule("while") { args ->
                        val condition = args[0]
                        val body = args[1]

                        while (DixLazyExpr.evalIfLazy(condition).boolValue()) {
                            DixLazyExpr.evalIfLazy(body)
                        }

                        DixObj.NoValue
                    },
            )

    override fun loadVariables(executorState: DixExecutorState): Array<ASCVariable<*>> =
            arrayOf()
}