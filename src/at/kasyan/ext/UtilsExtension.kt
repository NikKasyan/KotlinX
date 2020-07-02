package at.kasyan.ext

import java.math.BigInteger
import java.nio.charset.Charset
import java.security.SecureRandom
import java.util.*
import kotlin.math.absoluteValue


fun <T> concat(list: List<Array<T>>, fromTo: IntRange = 0..list.size): Array<T> {
    return list[fromTo].reduce { acc, bytes ->
        acc + bytes
    }
}

/**
 * Operator overloading for BigInteger + Long/Int
 */
operator fun BigInteger.rem(i: Int): BigInteger = this % i.toBigInteger()

operator fun BigInteger.rem(i: Long): BigInteger = this % i.toBigInteger()

operator fun BigInteger.plus(i: Int): BigInteger = this + i.toBigInteger()
operator fun BigInteger.plus(i: Long): BigInteger = this + i.toBigInteger()

operator fun BigInteger.div(i: Int): BigInteger = this / i.toBigInteger()
operator fun BigInteger.div(i: Long): BigInteger = this / i.toBigInteger()

operator fun BigInteger.minus(i: Int): BigInteger = this - i.toBigInteger()
operator fun BigInteger.minus(i: Long): BigInteger = this - i.toBigInteger()

operator fun BigInteger.times(i: Int): BigInteger = this * i.toBigInteger()
operator fun BigInteger.times(i: Long): BigInteger = this * i.toBigInteger()

operator fun BigInteger.compareTo(i: Int): Int = this.compareTo(i.toBigInteger())
operator fun BigInteger.compareTo(i: Long): Int = this.compareTo(i.toBigInteger())
/**
 * Base64 Easier decode and encode
 */
fun decodeBase64(src: String): ByteArray = Base64.getDecoder().decode(src)

fun decodeBase64(src: ByteArray): ByteArray = Base64.getDecoder().decode(src)

fun encodeBase64(src: String, charset: Charset = Charsets.UTF_8): ByteArray =
    encodeBase64(src.toByteArray(charset))

fun encodeBase64(src: ByteArray): ByteArray = Base64.getEncoder().encode(src)

fun urldecodeBase64(src: String, charset: Charset = Charsets.UTF_8): ByteArray = Base64.getUrlDecoder().decode(src)

fun urlencodeBase64(src: String, charset: Charset = Charsets.UTF_8): ByteArray =
    Base64.getUrlEncoder().encode(src.toByteArray(charset))


fun urlencodeBase64(src: ByteArray): ByteArray = Base64.getUrlEncoder().encode(src)

/**
 * Array Extensions
 */

operator fun ByteArray.get(intRange: IntRange): ByteArray {
    return this.sliceArray(intRange)
}

operator fun IntArray.get(intRange: IntRange): IntArray {
    return this.sliceArray(intRange)
}

operator fun LongArray.get(intRange: IntRange): LongArray {
    return this.sliceArray(intRange)
}

operator fun <T> Array<T>.get(intRange: IntRange): Array<T> {
    return this.sliceArray(intRange)
}

operator fun <E> List<E>.get(intRange: IntRange): List<E> {
    return this.slice(intRange)
}



