package dix.lexer

import org.ascore.lang.ASCLexer

/**
 * This class is used to lex the source code. Override and edit the [lex] method to change the
 * lexing behavior.
 */
class DixLexer(filePath: String) : ASCLexer(DixLexer::class.java.getResourceAsStream(filePath)) {
    override fun sortTokenRules() {
    }
}
