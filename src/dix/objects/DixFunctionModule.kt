package dix.objects

import org.ascore.lang.objects.ASCObject

/**
 * An example of an object for the Dix programming main.language
 */
class DixFunctionModule(val name: String, val callback: (args: List<ASCObject<*>>) -> ASCObject<*>) : ASCObject<Any>("Function") {
    fun call(args: List<ASCObject<*>>) = callback(args)

    override fun toString(): String = value.toString()
}
