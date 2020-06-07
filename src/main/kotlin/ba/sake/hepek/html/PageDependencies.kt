package ba.sake.hepek.html

interface PageDependencies {

    fun styleURLs(): List<String> = emptyList()
    fun stylesInline(): List<String> = emptyList()

    fun scriptURLs(): List<String> = emptyList()
    fun scriptsInline(): List<String> = emptyList()

    fun components(): List<Pair<BaseComponentSettings, ComponentDependencies>> = emptyList()
}