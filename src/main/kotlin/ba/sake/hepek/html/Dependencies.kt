package ba.sake.hepek.html

data class Dependency(
        val file: String,
        val version: String,
        val pkg: String,
        val baseFolder: String? = null, // usually "dist/", MUST end with slash!
        val qParams: String? = null
) {
    fun queryParams(): String? = qParams ?: "?$qParams"
}

data class Dependencies(
        val urls: List<String> = emptyList(),
        val inlines: List<String> = emptyList(),
        val deps: List<Dependency> = emptyList()
)