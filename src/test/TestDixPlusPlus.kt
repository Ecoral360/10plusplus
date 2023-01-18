package test

import dix.execution.DixExecutorState
import dix.execution.DixPreCompiler
import dix.lexer.DixLexer
import dix.module.DixModules
import dix.parser.DixParser
import org.ascore.executor.ASCExecutor
import org.ascore.executor.ASCExecutorBuilder
import org.json.JSONArray
import org.testng.annotations.Test


val CODES = arrayOf(
        // Hello, World!
        """
        println "Hello, World!" 
        """,

        // Setting variable
        """
        set a 0
        println a
        set b 3
        println b
        set a b 
        println a
        """,

        // Small program
        """
        println (+ 1 3 4)
        set a 0
        while (< a 5) (println a ; set a (+ a 1))
        set myDict { "hey" 3 3 "you" }
        println myDict
        """,

        // Dict
        """
        set myDict { "hey" 3 3 "you" }
        println myDict 
        """
).map { it.trimIndent() }


class TestDixPlusPlus {

    private fun runCode(code: String): JSONArray {
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

        val compilationResult = executor.compiler(code, true) // compile the code

        DixModules.load(executor.executorState)

        if (compilationResult.length() != 0) {
            return compilationResult
        }
        return executor.executerMain(false) // execute the code
    }

    @Test
    fun testNormalCodes() {
        CODES.forEachIndexed { index, code ->
            println("${"-".repeat(20)}\nRunning Code [$index]: ")
            val results = runCode(code)
            for (i in 0 until results.length()) {
                val obj = results.getJSONObject(i)
                assert(obj.getInt("id") != 400) { obj.getJSONArray("p").toString() }
            }
        }
    }
}