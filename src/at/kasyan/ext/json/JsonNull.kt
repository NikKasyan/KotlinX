package at.kasyan.ext.json

class JsonNull private constructor() : JsonElement() {
    companion object {
        val INSTANCE = JsonNull()
    }
}