package dix.objects

/**
 * An example of an object for the Dix programming main.language
 */
class DixFunctionModule(val name: String, val callback: (args: List<DixObj<*>>) -> DixObj<*>) : DixObj<Any>("Function") {
    fun call(args: List<DixObj<*>>): DixObj<*> = callback(args)

    override fun toString(): String = value.toString()
}
