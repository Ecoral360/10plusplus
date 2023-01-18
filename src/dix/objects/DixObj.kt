package dix.objects

import org.ascore.lang.objects.ASCObject

abstract class DixObj<T : Any>(value: T) : ASCObject<T>(value) {

    object NoValue : DixObj<Any>(0)


    open fun boolValue(): Boolean = true
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DixObj<*>) return false
        return other.value == this.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
