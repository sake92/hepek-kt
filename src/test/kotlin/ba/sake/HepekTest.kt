package ba.sake

import ba.sake.hepek.html.HtmlPage
import kotlinx.html.BODY
import org.junit.Assert.assertTrue
import org.junit.Test

class HepekTest {

    @Test
    fun testHepekContents() {
        println(Index.contents())
        assertTrue(1 == 1)
    }
}


object Index : HtmlPage {

    override fun BODY.pageContent() {
        +"Selam merhaba!"
    }
}