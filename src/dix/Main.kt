package dix

import dix.execution.DixExecutorState
import dix.execution.DixPreCompiler
import dix.lexer.DixLexer
import dix.module.DixModules
import dix.parser.DixParser
import org.ascore.executor.ASCExecutor
import org.ascore.executor.ASCExecutorBuilder

val CODE = """
    println (+ 1 3 4)
    set a 0
    while (< a 5) (println a ; set a (+ a 1))
    set myDict { "hey" 3 3 "you" }
    println myDict
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

    DixModules.load(executor.executorState)

    if (compilationResult.length() != 0) {
        println(compilationResult)
        return
    }
    val executionResult = executor.executerMain(false) // execute the code

    println(executionResult) // print the result

}
