package ba.sake.hepek.html

interface DependencyProvider {

    fun depPath(dep: Dependency): String

    companion object {

        val unpkg = object : DependencyProvider {
            override fun depPath(dep: Dependency): String {
                val maybeBaseFolder = dep.baseFolder ?: ""
                return "https://unpkg.com/${dep.pkg}@${dep.version}/${maybeBaseFolder}${dep.file}${dep.queryParams()}"
            }
        }

        val cdnjs = object : DependencyProvider {
            override fun depPath(dep: Dependency): String =
                    "https://cdnjs.cloudflare.com/ajax/libs/${dep.pkg}/${dep.version}/${dep.file}${dep.queryParams()}"
        }
    }
}


data class WebjarsDependencyProvider(val webjarsPath: String) : DependencyProvider {

    override fun depPath(dep: Dependency) =
            "$webjarsPath/${dep.pkg}/${dep.file}${dep.queryParams()}"
}
