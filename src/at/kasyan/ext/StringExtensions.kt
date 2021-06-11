package at.kasyan.util

import java.security.SecureRandom
import java.util.*
import kotlin.math.absoluteValue

/**
 * Creates a random String with a specified length which only
 * contains characters of the given alphabet
 *
 * @param alphabets constructs an alphabet by appending every char to a string
 * @param length the length of the created string
 * @param random the RNG(Random Number Generator) which provides random numbers
 * @return a random String
 */
fun String.Companion.randString(length: Int, vararg alphabets: CharRange, random: Random = SecureRandom()): String {
    val alphabet = alphabets.fold(
        ""
    ) { acc, crange ->
        acc + crange.fold(
            ""
        ) { accumulator, c ->
            accumulator + c
        }
    }
    return alphabet.randString(length, random)
}


/**
 * Creates a random String with a specified length which only contains
 * upper/lower cased letters and digits from 0 - 9
 *
 * Regex which matches this string: [A-Za-Z0-9]{n}
 * where n is the length of the string
 * @param length the length of the created string
 * @param random the RNG(Random Number Generator) which provides random numbers
 * @return a random String
 */
fun String.Companion.randAlphaNumericString(length: Int, random: Random = SecureRandom()): String {
    return String.randString(length, 'A'..'Z', 'a'..'z', '0'..'9', random = random)
}

/**
 * Creates a random String with a specified length which only
 * contains characters of the given alphabet
 *
 * @param alphabet the alphabet from which is chosen
 * @param length the length of the created string
 * @param random the RNG(Random Number Generator) which provides random numbers
 * @return a random String
 */
fun String.Companion.randString(alphabet: String, length: Int, random: Random = SecureRandom()) =
    alphabet.randString(length, random)


/**
 * Creates a random String with a specified length which only
 * contains characters of the given alphabet
 *
 * @param length the length of the created string
 * @param random the RNG(Random Number Generator) which provides random numbers
 * @return a random String
 */
fun String.randString(length: Int, random: Random = SecureRandom()): String {
    val bytes = ByteArray(length)
    random.nextBytes(bytes)
    return (bytes.indices).map { i -> this[(bytes[i] % this.length).absoluteValue] }.joinToString("")
}

/**
 * Repeats the given String n times.
 *
 * @param n how often the string is repeated
 *
 * @return the string repeated n times
 */
private operator fun String.times(n: Int): String = this.repeat(n)