package dix.objects

import org.ascore.lang.objects.ASCObject

/**
 * An example of an object for the Dix programming language
 */
class DixObjExample(obj: Any) : ASCObject<Any>(obj) {
    /**
     * This method is often called to unwrap the object from the ASCore object to the Java object
     *
     * In this case, it is unnecessary, because the ASCObject [getValue] method has the same functionality
     *
     * @return the value of the object
     */
    override fun getValue(): Any = value
}
