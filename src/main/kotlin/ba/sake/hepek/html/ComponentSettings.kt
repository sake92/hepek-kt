package ba.sake.hepek.html

interface BaseComponentSettings {
    val version: String
    val pkg: String
    val depsProvider: DependencyProvider
}

data class ComponentSettings(
        override val version: String,
        override val pkg: String,
        override val depsProvider: DependencyProvider = DependencyProvider.cdnjs
) : BaseComponentSettings

class ComponentDependencies(
        val cssDependencies: Dependencies = Dependencies(),
        val jsDependencies: Dependencies = Dependencies()
)