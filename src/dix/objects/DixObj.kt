package dix.objects

import org.ascore.lang.objects.ASCObject

abstract class DixObj<T : Any>(value: T) : ASCObject<T>(value) {

    object NoValue : DixObj<Any>(0)


    open fun boolValue(): Boolean = true
}
