package at.kasyan.ext.json

import java.lang.IllegalStateException

class JsonPrimitive : JsonElement {
    private val value: Any

    constructor(string: String) {
        this.value = string
    }

    constructor(boolean: Boolean) {
        this.value = boolean
    }

    constructor(number: Number) {
        this.value = number
    }

    fun isBoolean(): Boolean {
        return value is Boolean
    }

    override fun getAsBoolean(): Boolean {
        if (isBoolean()) return this.value as Boolean
        throw IllegalStateException("Primtive is not a boolean: ${this.value}")
    }

    fun isNumber(): Boolean {
        return value is Number
    }

    override fun getAsNumber(): Number {
        if (isBoolean()) return this.value as Number
        throw IllegalStateException("Primitive is not a number: ${this.value}")
    }

    fun isString(): Boolean {
        return value is String
    }

    override fun getAsString(): String {
        if (isString()) return this.value as String
        throw IllegalStateException("Primitive is not a String: ${this.value}")
    }

    fun getAsInt(): Int {
        if (isNumber()) return value as Int
        throw IllegalStateException("Primitive is not an Int: ${this.value}")
    }

    fun getAsDouble(): Double {
        if (isNumber()) return value as Double
        throw IllegalStateException("Primitive is not a Double: ${this.value}")
    }

    override fun toString(): String {
        return this.value.toString()
    }

}