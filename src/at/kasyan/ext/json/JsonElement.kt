package at.kasyan.ext.json

import java.lang.IllegalStateException
import java.lang.UnsupportedOperationException

abstract class JsonElement() {

    fun isJsonObject(): Boolean {
        return this is JsonObject
    }

    fun isNull(): Boolean {
        return this is JsonNull
    }

    fun isJsonPrimitive(): Boolean {
        return this is JsonPrimitive
    }

    fun getAsJsonObject(): JsonObject {
        if (isJsonObject()) return this as JsonObject
        throw IllegalStateException("Not a Json Object: $this")
    }

    fun getAsJsonPrimitive(): JsonPrimitive {
        if (isJsonPrimitive()) return this as JsonPrimitive
        throw IllegalStateException("Not a Json Primitive: $this")
    }

    public open fun getAsBoolean(): Boolean {
        throw UnsupportedOperationException(javaClass.simpleName)
    }

    public open fun getAsNumber(): Number {
        throw UnsupportedOperationException(javaClass.simpleName)
    }

    public open fun getAsString(): String {
        throw UnsupportedOperationException(javaClass.simpleName)
    }


}