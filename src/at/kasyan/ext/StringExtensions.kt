package at.kasyan.ext

import java.security.SecureRandom
import java.util.*
import kotlin.math.absoluteValue


fun String.Companion.randString(vararg alphabets: CharRange, length: Int, random: Random = SecureRandom()): String {
    val alphabet = alphabets.fold("",
        { acc, crange ->
            acc + crange.fold("",
                { accumalator, c ->
                    accumalator + c
                })
        })
    return alphabet.randString(length, random)
}
/**
 * Creates a String with a specified length which only
 * contains characters of the given alphabet
 *
 * @param alphabet the alphabet from which is chosen
 * @param length the length of the created string
 * @param random the RNG(Random Number Generator) which provides random numbers
 * @return a random String
 */
fun String.Companion.randString(alphabet: String, length: Int, random: Random = SecureRandom()) = alphabet.randString(length, random)

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
 * @return the string repeated n this
 */
private operator fun String.times(n: Int): String = this.repeat(n)
