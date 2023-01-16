package dix.objects

import org.ascore.tokens.Token

/**
 * An example of an object for the Dix programming main.language
 */
class DixInt(value: Int) : DixNumber(value) {
    constructor(token: Token) : this(token.value().toInt()) {}

    override fun getValue(): Int = super.getValue().toInt()
}
