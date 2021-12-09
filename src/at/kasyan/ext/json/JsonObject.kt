package at.kasyan.ext.json

class JsonObject(private val members: MutableMap<String, JsonElement> = mutableMapOf()) :
    MutableMap<String, JsonElement>, JsonElement() {
    override val entries: MutableSet<MutableMap.MutableEntry<String, JsonElement>>
        get() = members.entries
    override val keys: MutableSet<String>
        get() = members.keys
    override val size: Int
        get() = members.size
    override val values: MutableCollection<JsonElement>
        get() = members.values

    override fun containsKey(key: String): Boolean {
        return members.containsKey(key)
    }

    override fun containsValue(value: JsonElement): Boolean {
        return this.members.containsValue(value)
    }

    override fun get(key: String): JsonElement? {
        return this.members[key]
    }

    override fun isEmpty(): Boolean {
        return this.members.isEmpty()
    }

    override fun clear() {
        this.members.clear()
    }

    override fun put(key: String, value: JsonElement): JsonElement? {
        return this.members.put(key, value)
    }

    override fun putAll(from: Map<out String, JsonElement>) {
        return this.members.putAll(from)
    }

    override fun remove(key: String): JsonElement? {
        return this.members.remove(key)
    }

    override fun toString(): String {
        return this.members.toString()
    }

}