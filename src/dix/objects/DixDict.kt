package dix.objects

class DixDict(value: Map<DixObj<*>, DixObj<*>>) : DixObj<Map<DixObj<*>, DixObj<*>>>(value) {
    override fun toString(): String = value.map { (key, value) -> "$key: $value" }.joinToString(separator = ", ", prefix = "{", postfix = "}")

    override fun boolValue(): Boolean = value.isNotEmpty()
}
