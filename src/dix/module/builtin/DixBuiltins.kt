package ray.module.builtin

import dix.execution.DixExecutorState
import dix.objects.DixFunctionModule
import org.ascore.lang.objects.ASCObject
import org.ascore.lang.objects.ASCVariable
import ray.module.DixModule

object DixBuiltins : DixModule {
    override fun loadFunctions(executorState: DixExecutorState): Array<DixFunctionModule> =
            arrayOf(
                    DixFunctionModule("print") { args ->
                        println(args.joinToString(" "))
                        ASCObject.noValue<Any>()
                    }
            )

    override fun loadVariables(executorState: DixExecutorState): Array<ASCVariable<*>> =
            arrayOf()
}