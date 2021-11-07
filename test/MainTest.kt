
import at.kasyan.ext.replace

class Dog(weight: Int, field: String) {
    var weight: Int = weight
        get() = field
        set(value) {
            field = value
        }
}

fun main() {
 /*   val testLength = 20
    val testString = String.randString(testLength, 'A'..'Z', 'a'..'z', '0'..'9')
    assert(testString.length == testLength)
    println(testString)*/
    val test = "A-Za-z0-9"
    val pattern = ".-."
    val newString = test.replace(pattern){ group ->
        val first = group[0]
        val end = group[2]
        return@replace Array(end - first + 1){ (it + first.toInt()).toChar() }.joinToString("")
    }
    println(newString)
}
