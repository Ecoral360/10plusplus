package dix

import dix.execution.DixExecutorState
import dix.execution.DixPreCompiler
import dix.lexer.DixLexer
import dix.parser.DixParser
import org.ascore.executor.ASCExecutor
import org.ascore.executor.ASCExecutorBuilder

val CODE = """
    print 3 + -2
    print "hello" + " " + "world!"
    print "hello " + 3 + 2
    
    """.trimIndent()

fun main() {
    val lexer = DixLexer("/dix/grammar_rules/Grammar.yaml");
    val executor = ASCExecutorBuilder<DixExecutorState>() // create an executor builder
        .withLexer(lexer) // add the lexer to the builder
        .withParser { executorInstance: ASCExecutor<DixExecutorState> ->
            DixParser(
                executorInstance
            )
        } // add the parser to the builder
        .withExecutorState(DixExecutorState()) // add the executor state to the builder
        .withPrecompiler(DixPreCompiler()) // add the precompiler to the builder
        .build() // build the executor

    val compilationResult = executor.compiler(CODE, true) // compile the code

    if (compilationResult.length() != 0) {
        println(compilationResult)
        return
    }
    val executionResult = executor.executerMain(false) // execute the code

    println(executionResult) // print the result

}
