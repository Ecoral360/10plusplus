package dix.objects

class DixList(value: Array<DixObj<*>>) : DixObj<Array<DixObj<*>>>(value) {
    override fun toString(): String = value.contentToString()

    override fun boolValue(): Boolean = value.isNotEmpty()
}
