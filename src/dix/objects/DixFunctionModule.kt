package dix.objects

/**
 * An example of an object for the Dix programming main.language
 */
class DixFunctionModule(
        val name: String,
        private val evalIfLazy: Boolean = true,
        val callback: (args: List<DixObj<*>>) -> DixObj<*>,
) : DixObj<Any>("Function") {

    fun call(args: List<DixObj<*>>): DixObj<*> =
            if (evalIfLazy) callback(args.map { DixLazyExpr.evalIfLazy(it) })
            else callback(args)

    override fun toString(): String = value.toString()
}
