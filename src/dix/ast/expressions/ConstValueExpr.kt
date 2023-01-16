package dix.ast.expressions

import dix.objects.DixObj
import org.ascore.ast.buildingBlocs.Expression
import org.ascore.lang.objects.ASCObject


data class ConstValueExpr(private val value: DixObj<*>) : Expression<DixObj<*>> {
    /**
     * Appel\u00E9 durant le Runtime, cette m\u00E9thode retourne un objet de type ASObjet
     *
     * @return le r\u00E9sultat de l'expression
     */
    override fun eval(): DixObj<*> {
        return value
    }
}
