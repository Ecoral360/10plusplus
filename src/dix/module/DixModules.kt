package dix.module

import dix.execution.DixExecutorState
import org.ascore.lang.objects.ASCVariable
import dix.module.builtin.DixBuiltins

enum class DixModules(val module: DixModule) {
    Builtins(DixBuiltins)
    ;

    companion object {
        fun load(executorState: DixExecutorState) {
            values().forEach {
                val functions = it.module.loadFunctions(executorState)
                val variables = it.module.loadVariables(executorState)
                functions.forEach { func ->
                    executorState.scopeManager.currentScope.declareVariable(ASCVariable(func.name, func))
                }
                variables.forEach { variable ->
                    executorState.scopeManager.currentScope.declareVariable(variable)
                }
            }
        }
    }
}