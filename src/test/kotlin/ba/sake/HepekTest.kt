package ba.sake

import org.junit.Assert.assertEquals
import org.junit.Test

class MyLibraryTest {
    @Test
    fun testMyLanguage() {
        assertEquals("Kotlin", Hepek().kotlinLanguage().name)
        assertEquals(10, Hepek().kotlinLanguage().hotness)
    }
}