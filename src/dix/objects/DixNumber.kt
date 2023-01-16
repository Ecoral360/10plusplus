package dix.objects

import org.ascore.lang.objects.ASCObject
import org.ascore.tokens.Token

class DixNumber(value: Number) : ASCObject<Number>(value) {
    constructor(token: Token) : this(token.value.toDouble())

    override fun toString(): String =
            if (value.toDouble() == value.toInt().toDouble()) value.toInt().toString() else value.toString()
}
