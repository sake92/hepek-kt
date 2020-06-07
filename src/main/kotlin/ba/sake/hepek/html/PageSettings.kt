package ba.sake.hepek.html

data class PageSettings(
        val title: String = DefaultTitle,
        val label: String = DefaultTitle,
        val language: String = DefaultLanguage,
        val description: String? = null
) {
    companion object {
        val DefaultTitle = "changeme"
        val DefaultLanguage = "en"
    }
}
