package at.kasyan.ext

import java.nio.charset.Charset
import java.util.*

/**
 * @see java.util.Base64.Decoder.decode
 */
fun decodeBase64(src: String): ByteArray = Base64.getDecoder().decode(src)

/**
 * @see java.util.Base64.Decoder.decode
 */
fun decodeBase64(src: ByteArray): ByteArray = Base64.getDecoder().decode(src)

/**
 * @see java.util.Base64.Encoder.encode
 */
fun encodeBase64(src: String, charset: Charset = Charsets.UTF_8): ByteArray =
        encodeBase64(src.toByteArray(charset))

/**
 * @see java.util.Base64.Encoder.encode
 */
fun encodeBase64(src: ByteArray): ByteArray = Base64.getEncoder().encode(src)

/**
 * @see java.util.Base64.getUrlDecoder
 */
fun urldecodeBase64(src: String): ByteArray = Base64.getUrlDecoder().decode(src)


/**
 * @see java.util.Base64.getUrlEncoder
 */
fun urlencodeBase64(src: String, charset: Charset = Charsets.UTF_8): ByteArray =
        Base64.getUrlEncoder().encode(src.toByteArray(charset))

/**
 * @see java.util.Base64.getUrlEncoder
 */
fun urlencodeBase64(src: ByteArray): ByteArray = Base64.getUrlEncoder().encode(src)
