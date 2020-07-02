package at.kasyan.ext

import java.math.BigInteger


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