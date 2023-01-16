package dix.objects

import org.ascore.tokens.Token

/**
 * An example of an object for the Dix programming main.language
 */
class DixString(value: String) : DixObj<String>(value) {
    constructor(token: Token) : this(
            token.value().substring(1, token.value().length - 1) // removing the enclosing `"` from the string
    )

    override fun toString(): String = "\"$value\""

    override fun boolValue(): Boolean = value != ""
}
