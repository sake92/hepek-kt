package ba.sake

data class Language(val name: String, val hotness: Int)

class Hepek {

    fun kotlinLanguage() = Language("Kotlin", 10)
}