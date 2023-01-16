package dix.objects

import org.ascore.tokens.Token

class DixNumber(value: Number) : DixObj<Number>(value) {
    constructor(token: Token) : this(token.value.toDouble())
    constructor(value: Boolean) : this(if (value) 1 else 0)

    override fun toString(): String =
            if (value.toDouble() == value.toInt().toDouble()) value.toInt().toString() else value.toString()

    override fun boolValue(): Boolean = value.toDouble() != 0.0
}
