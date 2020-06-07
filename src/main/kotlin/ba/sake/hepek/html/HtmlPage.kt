package ba.sake.hepek.html

import kotlinx.html.*
import kotlinx.html.stream.appendHTML

interface HtmlPage : PageDependencies {
    fun siteSettings(): SiteSettings = SiteSettings()

    fun pageSettings(): PageSettings = PageSettings()

    fun contents(): String {
        // css
        val compStyleInlines = components().flatMap { it.second.cssDependencies.inlines }
        val allStyleInlines = compStyleInlines + stylesInline()
        val compStyleUrls: List<String> = components().flatMap { (cs, cd) ->
            cd.cssDependencies.urls + cd.cssDependencies.deps.map(cs.depsProvider::depPath)
        }
        val allStyleURLs = compStyleUrls + styleURLs()

        // js
        val compScriptInlines = components().flatMap { it.second.jsDependencies.inlines }
        val allScriptInlines = compScriptInlines + scriptsInline()
        val compScriptUrls = components().flatMap { (cs, cd) ->
            cd.jsDependencies.urls + cd.jsDependencies.deps.map(cs.depsProvider::depPath)
        }
        val allScriptURLs = compScriptUrls + scriptURLs()

        return "<!DOCTYPE html>" + StringBuilder().apply {
            appendHTML().html {
                head {
                    headContent()
                    allStyleURLs.map { u -> link { rel = "stylesheet"; href = u } }
                    allStyleInlines.map { s -> style { unsafe { +s } } }
                }
                body {
                    bodyContent()
                    allScriptURLs.map { u -> script { src = u } }
                    allScriptInlines.map { s -> script { unsafe { +s } } }
                }
            }
        }.toString()

    }

    fun HEAD.headContent(): Unit {
        val titleVal = pageSettings().title + (siteSettings().name ?: " - ${siteSettings().name}")
        title(titleVal)
        link { href = siteSettings().faviconNormal ?: "" }
    }

    fun BODY.bodyContent(): Unit {
        pageContent()
    }

    fun BODY.pageContent(): Unit {
        div { +"" }
    }
}