package at.kasyan.ext.json

import at.kasyan.ext.replace
import java.util.function.Predicate

class JsonParser(private val text: String) {
    companion object {
        private const val WHITE_SPACES = " \r\n\t"
        private const val ONE_TO_NINE = "123456789"
        private const val DIGIT = "0$ONE_TO_NINE"
        private const val HEX_DIGIT = "ABCDEFabcdef$DIGIT"
        private const val NUMBER_START = "-$DIGIT"
        private val NUMBER_PATTERN = "-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?".toPattern()
        private const val ESCAPED_CHARACTERS = "\"\\/bfnrt"
    }

    private var index = 0;
    fun parse(): JsonElement {
        if (text.isEmpty() || text.isBlank()) TODO("Text may not be empty or blank")
        val element = parseElement()
        if (index < text.length) TODO("You fucked up")
        return element
    }


    private fun isValidIndex(index: Int): Boolean {
        return index < text.length
    }

    private fun parseElement(): JsonElement {
        skipWhiteSpaces()
        val value = parseValue()
        skipWhiteSpaces()
        return value
    }

    private fun parseValue(): JsonElement {
        return when (text[index]) {
            '{' -> parseObject()
            '[' -> parseArray()
            else -> parsePrimitive()
        }
    }

    private fun parsePrimitive(): JsonElement {
        return when (text[index]) {
            '"' -> JsonPrimitive(parseString())
            in NUMBER_START -> parseNumber()
            else -> parseConstant()
        }
    }

    private fun parseConstant(): JsonElement {
        return when {
            text.substring(index).startsWith("true") -> parseTrue()
            text.substring(index).startsWith("false") -> parseFalse()
            text.substring(index).startsWith("null") -> parseNull()
            else -> TODO("INVALID CONSTANT")

        }
    }


    private fun parseTrue(): JsonPrimitive {
        requiredSkipConstant("true")
        return JsonPrimitive(true)
    }

    private fun parseFalse(): JsonPrimitive {
        requiredSkipConstant("false")
        return JsonPrimitive(false)
    }

    private fun parseNull(): JsonElement {
        requiredSkipConstant("null")
        return JsonNull.INSTANCE
    }

    private fun parseNumber(): JsonElement {
        val subText = text.substring(index)
        val matcher = NUMBER_PATTERN.matcher(subText)
        if (!matcher.find()) TODO("Expected Number")
        if (matcher.groupCount() > 1) TODO("Number may not start with 0")
        val group = matcher.group(0)
        index += group.length
        return JsonPrimitive(group)
    }

    private fun parseString(): String {
        val startIndex = index
        requiredSkip("\"", 1)
        skipCharacters()
        requiredSkip("\"", 1)
        return replaceEscapedCharacters(text.substring(startIndex + 1, index - 1))
    }

    private fun replaceEscapedCharacters(string: String): String {
        return string.replace("\\\\u[$HEX_DIGIT]{4}") {
            it.substring(2).toInt(16).toChar().toString()
        }
    }

    private fun parseArray(): JsonElement {
        requiredSkip("[", 1)
        skipWhiteSpaces()
        if (text[index] == ']') {
            requiredSkip("]")
            return JsonArray()
        }

        val array = JsonArray()
        parseElements(array)
        skipWhiteSpaces()
        requiredSkip("]")
        return array
    }

    private fun parseElements(array: JsonArray) {
        skipWhiteSpaces()
        val element = parseElement()
        array.add(element)
        if (text[index] == ',') {
            requiredSkip(",")
            parseElements(array)
        }
    }

    private fun parseObject(): JsonObject {
        requiredSkip("{")
        skipWhiteSpaces()
        if (text[index] == '}') {
            requiredSkip("}")
            return JsonObject()
        }
        val obj = JsonObject()
        parseMembers(obj)
        skipWhiteSpaces()
        requiredSkip("}")
        return obj
    }

    private fun parseMembers(obj: JsonObject) {
        skipWhiteSpaces()
        val (key, value) = parseMember()
        obj[key] = value
        if (text[index] == ',') {
            requiredSkip(",")
            parseMembers(obj)
        }
    }

    private fun parseMember(): Pair<String, JsonElement> {
        skipWhiteSpaces()
        val name = parseString()
        skipWhiteSpaces()
        requiredSkip(":", 1)
        val element = parseElement()
        return name to element
    }

    private fun skipWhiteSpaces() {
        skip(WHITE_SPACES)
    }

    private fun skipCharacters() {
        skip { c: Char -> isCharacter(c) || isEscapedCharacterAndSkip(c) }
    }

    private fun isEscapedCharacterAndSkip(c: Char): Boolean {
        if (c != '\\') return false
        index++
        if (text[index] in ESCAPED_CHARACTERS) return true
        if (text[index] == 'u') return isHexCharacter()
        return false
    }

    private fun isHexCharacter(): Boolean {
        requiredSkip("u", 1)
        requiredSkip(HEX_DIGIT, 3)
        return true
    }

    private fun requiredSkipConstant(string: String, numberOfSkips: Int = 1) {
        val startIndex = index
        for (i in 1..numberOfSkips) {
            for (c in string) {
                skip(c.toString())
            }
        }
        if (startIndex == index) TODO("Expected ${string.substring(index)} $index")
    }

    private fun requiredSkip(string: String, numberOfSkips: Int = 1) {
        val startIndex = index
        skip(string, numberOfSkips)
        if (startIndex == index) TODO("Expected ${string.substring(index)} $index")
    }

    private fun skip(string: String, numberOfSkips: Int) {
        val endIndex = index + numberOfSkips
        while (index < endIndex && isValidIndex(index) && text[index] in string) {
            index++
        }
    }

    private fun skip(string: String) {
        while (isValidIndex(index) && text[index] in string) index++
    }

    private fun skip(predicate: Predicate<Char>) {
        while (isValidIndex(index) && predicate.test(text[index])) index++
    }

    private fun isCharacter(char: Char): Boolean {
        val range = ' '..Char.MAX_VALUE;
        if (char == '"' || char == '\\') return false
        return char in range
    }

}


fun main() {
    val parser = JsonParser("\"Test\\u0020TestASDKNDALN\\u0041\"")
    println(parser.parse().getAsString())
    val parser2 = JsonParser("\"\"")
    println(parser2.parse().equals(""))
    val parser3 = JsonParser(
        """
        {"asd":123, "test": true, "ASD123": {"test":"asd"}}
    """.trimIndent()
    )
    println(parser3.parse().getAsJsonObject())
    val parser4 = JsonParser("{             }")
    println(parser4.parse())
    val parser6 = JsonParser("{\"asd\": 42}")
    println(parser6.parse())
    val parser5 = JsonParser("[ ]")
    println(parser5.parse())

}