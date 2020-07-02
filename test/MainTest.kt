import at.kasyan.ext.randString

class Dog(weight: Int, field: String) {
    var weight: Int = weight
        get() = field
        set(value) {
            field = value
        }
}
fun main() {
    val testLength = 20
    val testString = String.randString(testLength, 'A'..'Z', 'a'..'z', '0'..'9')
    assert(testString.length == testLength)
    println(testString)
}