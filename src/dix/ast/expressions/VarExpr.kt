package dix.ast.expressions

import dix.execution.DixExecutorState
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject
import org.ascore.lang.objects.ASCVariable


data class VarExpr(private val name: String, val executorState: DixExecutorState) : Expression<ASCObject<*>> {
    /**
     * Appel\u00E9 durant le Runtime, cette m\u00E9thode retourne un objet de type ASObjet
     *
     * @return le r\u00E9sultat de l'expression
     */
    override fun eval(): ASCVariable<*> {
        return executorState.scopeManager.currentScopeInstance.getVariable(name)
    }
}
