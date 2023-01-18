package dix.module.builtin

import dix.ast.expressions.LazyExpr
import dix.ast.expressions.VarExpr
import dix.execution.DixExecutorState
import dix.module.DixModule
import dix.objects.*
import org.ascore.lang.objects.ASCVariable
import kotlin.math.pow

object DixNumberModule : DixModule {
    override fun loadFunctions(executorState: DixExecutorState): Array<DixFunctionModule> =
            arrayOf(
                    DixFunctionModule("+") { args ->
                        DixNumber(args.sumOf { (it.value as Number).toDouble() })
                    },

                    DixFunctionModule("-") { args ->
                        DixNumber(args.subList(1, args.size - 1).fold((args[0].value as Number).toDouble()) { acc, dixObj ->
                            acc - (dixObj.value as Number).toDouble()
                        })
                    },

                    DixFunctionModule("*") { args ->
                        DixNumber(args.subList(1, args.size - 1).fold((args[0].value as Number).toDouble()) { acc, dixObj ->
                            acc * (dixObj.value as Number).toDouble()
                        })
                    },

                    DixFunctionModule("^") { args ->
                        DixNumber(args.subList(1, args.size - 1).fold((args[0].value as Number).toDouble()) { acc, dixObj ->
                            acc.pow((dixObj.value as Number).toDouble())
                        })
                    },

                    DixFunctionModule("/") { args ->
                        DixNumber(args.subList(1, args.size - 1).fold((args[0].value as Number).toDouble()) { acc, dixObj ->
                            acc / (dixObj.value as Number).toDouble()
                        })
                    },

                    DixFunctionModule("%") { args ->
                        DixNumber(args.subList(1, args.size - 1).fold((args[0].value as Number).toDouble()) { acc, dixObj ->
                            acc % (dixObj.value as Number).toDouble()
                        })
                    },

                    DixFunctionModule("++") { args ->
                        DixNumber((args[0].value as Number).toDouble() + 1)
                    },

                    DixFunctionModule("--") { args ->
                        DixNumber((args[0].value as Number).toDouble() - 1)
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
            )

    override fun loadVariables(executorState: DixExecutorState): Array<ASCVariable<*>> =
            arrayOf()
}