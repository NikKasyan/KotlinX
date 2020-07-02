package at.kasyan.ext


/**
 * Concats the elements of the list to one big array.
 * With a specified range
 *
 */
fun <T> concat(list: List<Array<T>>, fromTo: IntRange = 0..list.size): Array<T> {
    return list[fromTo].reduce { acc, bytes ->
        acc + bytes
    }
}

/**
 * Slices the Array into a subarray
 */
operator fun ByteArray.get(intRange: IntRange): ByteArray {
    return this.sliceArray(intRange)
}

/**
 * Slices the Array into a subarray
 */
operator fun IntArray.get(intRange: IntRange): IntArray {
    return this.sliceArray(intRange)
}

/**
 * Slices the Array into a subarray
 */
operator fun LongArray.get(intRange: IntRange): LongArray {
    return this.sliceArray(intRange)
}

/**
 * Slices the Array into a subarray
 */
operator fun <T> Array<T>.get(intRange: IntRange): Array<T> {
    return this.sliceArray(intRange)
}

/**
 * Slices the List into a subList
 */
operator fun <E> List<E>.get(intRange: IntRange): List<E> {
    return this.slice(intRange)
}
