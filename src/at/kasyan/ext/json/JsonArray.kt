package at.kasyan.ext.json

class JsonArray : MutableList<JsonElement>, JsonElement() {
    private val elements: MutableList<JsonElement> = mutableListOf()
    override val size: Int
        get() = elements.size

    override fun contains(element: JsonElement): Boolean {
        return elements.contains(element)
    }

    override fun containsAll(elements: Collection<JsonElement>): Boolean {
        return elements.containsAll(elements)
    }

    override fun get(index: Int): JsonElement {
        return elements[index]
    }

    override fun indexOf(element: JsonElement): Int {
        return elements.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun iterator(): MutableIterator<JsonElement> {
        return elements.iterator()
    }

    override fun lastIndexOf(element: JsonElement): Int {
        return elements.lastIndexOf(element)
    }

    override fun add(element: JsonElement): Boolean {
        return elements.add(element)
    }

    override fun add(index: Int, element: JsonElement) {
        return elements.add(index, element)
    }

    override fun addAll(index: Int, elements: Collection<JsonElement>): Boolean {
        return this.elements.addAll(index, elements)
    }

    override fun addAll(elements: Collection<JsonElement>): Boolean {

        return this.elements.addAll(elements)
    }

    override fun clear() {
        this.elements.clear()
    }

    override fun listIterator(): MutableListIterator<JsonElement> {
        return this.elements.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<JsonElement> {
        return this.elements.listIterator(index)
    }

    override fun remove(element: JsonElement): Boolean {
        return this.elements.remove(element)
    }

    override fun removeAll(elements: Collection<JsonElement>): Boolean {
        return this.elements.removeAll(elements)
    }

    override fun removeAt(index: Int): JsonElement {
        return this.elements.removeAt(index)
    }

    override fun retainAll(elements: Collection<JsonElement>): Boolean {
        return this.elements.retainAll(elements)
    }

    override fun set(index: Int, element: JsonElement): JsonElement {
        return this.elements.set(index, element)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<JsonElement> {
        return this.elements.subList(fromIndex, toIndex)
    }

    override fun toString(): String {
        return elements.toString()
    }
}