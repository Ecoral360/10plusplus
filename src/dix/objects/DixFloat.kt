package dix.objects

import org.ascore.tokens.Token

/**
 * An example of an object for the Dix programming main.language
 */
class DixFloat(value: Double) : DixNumber(value) {
    constructor(token: Token) : this(token.value().toDouble()) {}

    override fun getValue(): Double = super.getValue().toDouble()
}
