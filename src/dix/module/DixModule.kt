package ray.module

import dix.execution.DixExecutorState
import dix.objects.DixFunctionModule
import org.ascore.lang.objects.ASCVariable

interface DixModule {
    fun loadFunctions(executorState: DixExecutorState): Array<DixFunctionModule>
    fun loadVariables(executorState: DixExecutorState): Array<ASCVariable<*>>
}