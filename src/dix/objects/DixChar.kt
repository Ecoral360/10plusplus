package dix.objects

import org.ascore.lang.objects.ASCObject
import org.ascore.tokens.Token

class DixChar(value: Char) : ASCObject<Char>(value) {
    constructor(token: Token) : this(token.value[0])

    override fun toString(): String = "'$value'"
}
